package com.jayden.graphqlapi.utils;

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
}
