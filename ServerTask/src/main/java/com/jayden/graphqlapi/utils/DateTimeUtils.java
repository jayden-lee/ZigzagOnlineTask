package com.jayden.graphqlapi.utils;

import com.jayden.graphqlapi.exception.InvalidArgumentException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public final class DateTimeUtils {

    public static LocalDateTime getThisWeekFirstDateTime(LocalDateTime baseDateTime) {
        return baseDateTime.with(DayOfWeek.MONDAY)
            .withHour(0)
            .withMinute(0)
            .withSecond(0);
    }

    public static LocalDateTime getThisWeekLastDateTime(LocalDateTime baseDateTime) {
        return baseDateTime.with(DayOfWeek.SUNDAY)
            .withHour(23)
            .withMinute(59)
            .withSecond(59);
    }

    public static void validateStartDateTimeAndEndDateTime(LocalDateTime startDt, LocalDateTime endDt) {
        if (startDt.isAfter(endDt)) {
            throw new RuntimeException("Start date value cannot be greater than last date value.");
        }
    }

    public static LocalDateTime parse(String dtString) {
        try {
            return LocalDateTime.parse(dtString);
        } catch (Exception e) {
            throw new InvalidArgumentException("The time value is invalid.", dtString);
        }
    }
}
