package com.rw.streaminganalyzer.util;

import com.rw.streaminganalyzer.model.common.Country;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtil {
    private final Country DEFAULT_COUNTRY = Country.NL;

    public LocalDateTime convertToDefaultLocalDateTime(LocalDateTime dateTime, Country country) {
        return dateTime.atZone(country.getTimeZone().toZoneId())
                .withZoneSameInstant(DEFAULT_COUNTRY.getTimeZone().toZoneId())
                .toLocalDateTime();
    }
}
