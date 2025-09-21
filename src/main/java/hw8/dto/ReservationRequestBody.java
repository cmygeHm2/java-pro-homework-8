package hw8.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ReservationRequestBody {
    @NotNull
    @Positive
    private Long clientId;

    @NotNull
    @Positive
    private BigDecimal clientLimit;
}