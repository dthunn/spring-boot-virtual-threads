package com.dthunn.vthreads.controller;

import com.dthunn.vthreads.dto.FlightReservationResponse;
import com.dthunn.vthreads.dto.TripPlan;
import com.dthunn.vthreads.dto.TripReservationRequest;
import com.dthunn.vthreads.service.TripPlanService;
import com.dthunn.vthreads.service.TripReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
public class TripController {

    private final TripPlanService planService;
    private final TripReservationService reservationService;

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode){
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request){
        return this.reservationService.reserve(request);
    }

}
