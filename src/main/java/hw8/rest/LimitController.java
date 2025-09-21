package hw8.rest;

import hw8.dto.LimitReservationResponseDto;
import hw8.dto.ReservationRequestBody;
import hw8.service.LimitsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/limits")
public class LimitController {

    private final LimitsService limitsService;

    @PostMapping(value = "/reserve", consumes = "application/json")
    public LimitReservationResponseDto reserveLimit(@RequestBody @Valid ReservationRequestBody requestBody) {
        UUID reservationUuid = limitsService.reserveLimit(requestBody);
        return new LimitReservationResponseDto(reservationUuid);
    }

    @PostMapping(value = "/commit/{uuid}")
    public String commitReservation(@PathVariable(value = "uuid") UUID uuid) {
        limitsService.commitReservation(uuid);
        return "ok";
    }

}
