package com.example.SimpleComplex.Records;

import java.time.LocalDate;

public record WeeklyUpdate(
        int userId,
        int id,
        LocalDate date,
        int occupancy,
        int prospects,
        int sales,
        double singlePrice,
        double doublePrice,
        double triplePrice
){}
