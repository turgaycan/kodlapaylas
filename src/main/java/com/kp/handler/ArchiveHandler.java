package com.kp.handler;

import com.kp.util.DateUtils;
import com.kp.util.KpUrlPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcan on 20/12/15.
 */
@Controller
public class ArchiveHandler {

    @Autowired
    protected DateUtils dateUtils;

    @RequestMapping(value = "/archive-years", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView listArchiveArticles() {
        ModelAndView mav = new ModelAndView(KpUrlPaths.ARCHIVE_VIEW);
        List<Integer> possibleArchiveYears = new ArrayList<>();
        for (int index = dateUtils.BORN_YEAR; index <= dateUtils.currentYear(); index++) {
            possibleArchiveYears.add(index);
        }
        mav.addObject("years", possibleArchiveYears);
        return mav;
    }

}
