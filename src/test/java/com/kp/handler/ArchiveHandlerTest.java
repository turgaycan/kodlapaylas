package com.kp.handler;


import com.kp.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 26/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArchiveHandlerTest {

    @InjectMocks
    private ArchiveHandler handler;

    @Mock
    private DateUtils dateUtils;

    @Test
    public void shouldListArchiveArticles(){
        when(dateUtils.currentYear()).thenReturn(2015);
        final ModelAndView mav = handler.listArchiveArticles();

        assertEquals("archive", mav.getViewName());
        final List<Integer> years = (List<Integer>) mav.getModel().get("years");
        assertEquals(2012, years.get(0).intValue());
        assertEquals(2013, years.get(1).intValue());
        assertEquals(2014, years.get(2).intValue());
        assertEquals(2015, years.get(3).intValue());
    }

}