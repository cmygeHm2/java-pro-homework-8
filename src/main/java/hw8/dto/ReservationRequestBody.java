package hw8.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
public class ReservationRequestBody {
    @NotNull
    private final Long clientId;

    @NotNull
    @Positive
    private final BigDecimal clientLimit;
}