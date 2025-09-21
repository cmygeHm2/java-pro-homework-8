package hw8.jobs;

import hw8.config.AppSettings;
import hw8.entity.Limit;
import hw8.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final LimitRepository limitRepository;
    private final AppSettings appSettings;

    @Override
    public void run(String... args) {
        LongStream.rangeClosed(1, 100)
                .forEach(clientId -> {
                    Limit limit = new Limit(clientId, appSettings.getDefaultLimit());
                    limitRepository.save(limit);
                });
    }
}


