package hw8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class LimitReservationResponseDto implements Serializable {
    private UUID reservationUuid;
}