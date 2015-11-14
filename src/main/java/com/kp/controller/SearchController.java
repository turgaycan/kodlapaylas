package com.kp.controller;

import com.kp.service.search.SearchService;
import com.kp.util.KpUrlPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by tcan on 03/10/15.
 */
@Controller
@RequestMapping(value = KpUrlPaths.SEARCH)
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public ModelAndView search(@PathVariable String keyword) {

        return new ModelAndView("/search");
    }

    @RequestMapping(value = "/kategori/{keyword}", method = RequestMethod.GET)
    public ModelAndView searchCategory(@PathVariable String keyword) {

        return new ModelAndView("/search");
    }


}
