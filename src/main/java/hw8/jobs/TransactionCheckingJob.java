package hw8.jobs;

import hw8.config.AppSettings;
import hw8.repository.LimitReservationRepository;
import hw8.service.LimitsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionCheckingJob {

    private final LimitReservationRepository limitReservationRepository;
    private final LimitsService limitsService;
    private final AppSettings appSettings;

    @Scheduled(cron = "${settings.transaction-check-schedule}")
    public void reset() {
        OffsetDateTime threshold = OffsetDateTime.now().minus(appSettings.getTransactionCommitWaitingTime());
        limitReservationRepository.findByCreateTimeIsLessThan(threshold)
                .forEach(limitsService::rollbackReservation);
    }
}
