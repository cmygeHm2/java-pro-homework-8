package hw8.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.time.Duration;

@ConfigurationProperties("settings")
@Getter
@Setter
public class AppSettings {
    private BigDecimal defaultLimit;
    private Duration transactionCommitWaitingTime;
}
