package hw8.jobs;

import hw8.config.AppSettings;
import hw8.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LimitResetJob {

    private final LimitRepository limitRepository;
    private final AppSettings appSettings;

    @Scheduled(cron = "${settings.reset-schedule}")
    public void reset() {
        log.info("Reset limits");
        limitRepository.findAll()
                .forEach(limit -> {
                    limit.setClientLimit(appSettings.getDefaultLimit());
                    limitRepository.save(limit);
                });
    }
}
