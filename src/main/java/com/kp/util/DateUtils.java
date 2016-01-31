package com.kp.util;

import com.kp.dto.DateRange;
import com.kp.dto.MonthUIModel;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public Date dateWithMonthAtStartDay(Integer year, Integer month) {
        Instant instant = LocalDate.of(year, month - 1, 01).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public Date now() {
        return new Date();
    }

    public boolean isValidYear(Integer year) {
        LocalDate actualDate = LocalDate.of(year, Month.FEBRUARY, 01);
        return actualDate.isAfter(BORN_DATE) &&
                actualDate.isBefore(LocalDate.now());
    }

    public List<MonthUIModel> monthUiModels() {
        List<MonthUIModel> monthUIModels = new ArrayList<>();
        for (Month month : Month.values()) {
            final String monthDisplayName = month.getDisplayName(TextStyle.FULL, new Locale("tr"));
            monthUIModels.add(new MonthUIModel(month.getValue(), monthDisplayName));
        }
        return monthUIModels;

    }

    public DateRange dateWithMonthAtEndDay(Integer year, Integer month) {
        LocalDate initial = LocalDate.of(year, month - 1, 01);

        Instant start = initial.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date startDate = Date.from(start);
        Instant end = initial.withDayOfMonth(initial.lengthOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date endDate = Date.from(end);

        return new DateRange(startDate, endDate);
    }

    public DateRange prepareDateRange(Integer year) {
        Date startDate = currentYear(year);
        Date endDate = dateOfNextYear(year);
        return new DateRange(startDate, endDate);
    }

    public boolean isNotValidYear(Integer year) {
        return !isValidYear(year);
    }

    public int currentYear() {
        return LocalDate.now().getYear();
    }

    public List<Integer> possibleArchiveYears() {
        List<Integer> possibleArchiveYears = new ArrayList<>();
        for (int index = BORN_YEAR; index <= currentYear(); index++) {
            possibleArchiveYears.add(index);
        }
        return possibleArchiveYears;
    }

}
