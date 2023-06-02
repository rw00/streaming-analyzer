package com.rw.streaminganalyzer.util;

import java.time.LocalDate;
import java.time.Period;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AgeCalculator {
    // TO DO : maybe LocalDate at residence country is different from LocalDate in Amsterdam; revise
    public int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
