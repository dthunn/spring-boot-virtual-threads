package com.dthunn.vthreads.dto;

import java.time.LocalDate;

public record Flight(String flightNumber,
                     String airline,
                     int price,
                     LocalDate date,
                     int flightDurationInMinutes) {
}
