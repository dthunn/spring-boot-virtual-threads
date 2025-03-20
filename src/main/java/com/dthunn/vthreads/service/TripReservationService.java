package com.dthunn.vthreads.service;

import com.dthunn.vthreads.client.FlightReservationServiceClient;
import com.dthunn.vthreads.client.FlightSearchServiceClient;
import com.dthunn.vthreads.dto.Flight;
import com.dthunn.vthreads.dto.FlightReservationRequest;
import com.dthunn.vthreads.dto.FlightReservationResponse;
import com.dthunn.vthreads.dto.TripReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final FlightSearchServiceClient searchServiceClient;
    private final FlightReservationServiceClient reservationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest request){
        var flights = this.searchServiceClient.getFlights(request.departure(), request.arrival());
        var bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
        var flight = bestDeal.orElseThrow(() -> new IllegalStateException("no flights found"));
        var reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(), request.date());
        return this.reservationServiceClient.reserve(reservationRequest);
    }

}
