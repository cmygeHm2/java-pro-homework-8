package hw8;

import hw8.config.AppSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(AppSettings.class)
@EnableScheduling
public class LimitsApp {
    public static void main(String[] args) {
        SpringApplication.run(LimitsApp.class, args);
    }
}
