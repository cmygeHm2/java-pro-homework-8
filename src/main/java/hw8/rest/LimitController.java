package hw8.rest;

import hw8.dto.ReservationRequestBody;
import hw8.service.LimitsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/limits")
public class LimitController {

    private final LimitsService limitsService;

    @PostMapping("/reserve")
    public String reserveLimit(@RequestBody @Valid ReservationRequestBody requestBody) {
        limitsService.reserveLimit(requestBody);

        return "ok";
    }

}








