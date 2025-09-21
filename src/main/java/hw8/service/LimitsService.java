package hw8.service;

import hw8.dto.ReservationRequestBody;
import hw8.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LimitsService {
    private final LimitRepository limitRepository;

    public void reserveLimit(ReservationRequestBody requestBody) {

    }
}
