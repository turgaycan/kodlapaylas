package com.kp.util;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by turgaycan on 9/28/15.
 */
@Component
public class DateUtils {

    final DateTimeFormatter FULL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final int BORN_YEAR = 2012;
    final LocalDate BORN_DATE = LocalDate.of(BORN_YEAR, Month.JANUARY, 01);

    public LocalDate initOfYear(Integer year) {
        return LocalDate.of(year, Month.JANUARY, 01);
    }

    public LocalDateTime prevYear(Integer year) {
        return initOfYear(year).minusYears(1).atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();
    }

    public LocalDateTime nextYear(Integer year) {
        return initOfYear(year).plusYears(1).atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();
    }

    public Date dateOfPrevYear(Integer year) {
        Instant instant = prevYear(year).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public Date dateOfNextYear(Integer year) {
        Instant instant = nextYear(year).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public Date currentYear(Integer year) {
        Instant instant = initOfYear(year).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public Date now() {
        return new Date();
    }


    public boolean isValidYear(Integer year) {
        LocalDate actualDate = LocalDate.of(year, Month.JANUARY, 01);
        return actualDate.isAfter(BORN_DATE) &&
                actualDate.isBefore(LocalDate.now());
    }

    public boolean isNotValidYear(Integer year){
        return !isValidYear(year);
    }

    public int currentYear() {
        return LocalDate.now().getYear();
    }
}
