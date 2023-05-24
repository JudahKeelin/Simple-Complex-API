package com.example.SimpleComplex.Records;

import java.util.Date;

public record WeeklyUpdate(
        int id,
        Date date,
        int occupancy,
        int prospects,
        int sales,
        double singlePrice,
        double doublePrice,
        double triplePrice
) {
}
