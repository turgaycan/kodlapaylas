package com.kp.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by tcan on 04/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class DateUtilsTest {

    @InjectMocks
    private DateUtils dateUtils;

    @Test
    public void testInitOfYear() throws Exception {
        LocalDate currentDate = dateUtils.initOfYear(2014);
        assertThat(currentDate.getYear(), is(2014));
        assertThat(currentDate.getMonth(), is(Month.JANUARY));
        assertThat(currentDate.getDayOfYear(), is(1));
    }


    @Test
    public void testPrevYear() throws Exception {
        LocalDateTime currentDate = dateUtils.prevYear(2014);
        assertThat(currentDate.getYear(), is(2013));
        assertThat(currentDate.getMonth(), is(Month.JANUARY));
        assertThat(currentDate.getDayOfYear(), is(1));
    }

    @Test
    public void testNextYear() throws Exception {
        LocalDateTime currentDate = dateUtils.nextYear(2014);
        assertThat(currentDate.getYear(), is(2015));
        assertThat(currentDate.getMonth(), is(Month.JANUARY));
        assertThat(currentDate.getDayOfYear(), is(1));
    }
}