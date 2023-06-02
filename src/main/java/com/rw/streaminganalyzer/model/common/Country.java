package com.rw.streaminganalyzer.model.common;

import java.util.TimeZone;

public enum Country {
    NL("Netherlands", TimeZone.getTimeZone("Europe/Amsterdam")),
    CA("Canada", TimeZone.getTimeZone("America/Toronto")),
    CN("China", TimeZone.getTimeZone("Asia/Shanghai")),
    ID("Indonesia", TimeZone.getTimeZone("Asia/Jakarta")),
    PT("Portugal", TimeZone.getTimeZone("Europe/Lisbon")),
    RU("Russia", TimeZone.getTimeZone("Europe/Moscow")),
    US("United States", TimeZone.getTimeZone("America/Los_Angeles"));

    private final String countryName;
    private final TimeZone timeZone;

    Country(String countryName, TimeZone timeZone) {
        this.countryName = countryName;
        this.timeZone = timeZone;
    }

    public String getCountryName() {
        return countryName;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }
}
