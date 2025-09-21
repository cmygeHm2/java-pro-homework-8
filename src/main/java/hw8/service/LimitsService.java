package hw8.service;

import hw8.config.AppSettings;
import hw8.dto.ReservationRequestBody;
import hw8.entity.Limit;
import hw8.entity.LimitReservation;
import hw8.entity.LimitReservationArchive;
import hw8.enums.TransactionStatusEnum;
import hw8.exception.RecordNotFoundException;
import hw8.exception.NotEnoughMoneyException;
import hw8.repository.LimitRepository;
import hw8.repository.LimitReservationArchiveRepository;
import hw8.repository.LimitReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LimitsService {
    private final LimitRepository limitRepository;
    private final LimitReservationRepository limitReservationRepository;
    private final LimitReservationArchiveRepository limitReservationArchiveRepository;
    private final AppSettings appSettings;

    @Transactional
    public UUID reserveLimit(ReservationRequestBody requestBody) {
        Limit limit = limitRepository.findById(requestBody.getClientId())
                .orElseGet(() -> {
                    Limit newLimit = new Limit();
                    newLimit.setClientId(requestBody.getClientId());
                    newLimit.setClientLimit(appSettings.getDefaultLimit());
                    return limitRepository.save(newLimit);
                });
        BigDecimal currentLimit = limit.getClientLimit();
        limit.setClientLimit(currentLimit.subtract(requestBody.getClientLimit()));
        if (limit.getClientLimit().compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException(requestBody.getClientId());
        }
        LimitReservation limitReservation = new LimitReservation();
        limitReservation.setClientId(requestBody.getClientId());
        limitReservation.setSum(requestBody.getClientLimit());
        limitReservationRepository.save(limitReservation);
        return limitReservation.getUuid();
    }

    @Transactional
    public void rollbackReservation(LimitReservation reservation) {
        var limitReservationArchive = new LimitReservationArchive();
        limitReservationArchive.setUuid(reservation.getUuid());
        limitReservationArchive.setClientId(reservation.getClientId());
        limitReservationArchive.setFinishTime(OffsetDateTime.now());
        limitReservationArchive.setTransactionStatus(TransactionStatusEnum.ROLLBACK);
        var limit = limitRepository.findById(reservation.getClientId()).orElseThrow(RecordNotFoundException::new);
        limit.setClientLimit(limit.getClientLimit().add(reservation.getSum()));
        limitRepository.save(limit);
        limitReservationArchiveRepository.save(limitReservationArchive);
        limitReservationRepository.delete(reservation);
    }

    @Transactional
    public void commitReservation(UUID uuid) {
        LimitReservation reservation = limitReservationRepository.findById(uuid).orElseThrow(RecordNotFoundException::new);
        var limitReservationArchive = new LimitReservationArchive();
        limitReservationArchive.setUuid(uuid);
        limitReservationArchive.setClientId(reservation.getClientId());
        limitReservationArchive.setFinishTime(OffsetDateTime.now());
        limitReservationArchive.setTransactionStatus(TransactionStatusEnum.COMMIT);
        limitReservationArchiveRepository.save(limitReservationArchive);
        limitReservationRepository.delete(reservation);
    }
}
