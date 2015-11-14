package com.kp.handler;

import com.kp.domain.ArticleType;
import com.kp.dto.CategoryUIModel;
import com.kp.service.article.ArticleTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tcan on 08/11/15.
 */
@Controller
public class CategoryComponentHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryComponentHandler.class);

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping(value = "/root-categories", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView listRootCategories() {
        ModelAndView mav = new ModelAndView("/contents/commons/root-categories");
        LOGGER.info("Root Categories page..");
        mav.addObject("categories", getAllRootTypes());
        return mav;
    }

    private List<ArticleType> getAllRootTypes() {
        return articleTypeService.findAllRootTypes();
    }

    @RequestMapping(value = "/all-categories", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView populateCategories() {
        ModelAndView mav = new ModelAndView("/contents/commons/all-categories");
        LOGGER.info("Root Categories page..");
        List<CategoryUIModel> categoryUIModels = new ArrayList<>();
        final List<ArticleType> allCategories = articleTypeService.findAll();
        for (ArticleType category : getAllRootTypes()) {
            List<ArticleType> subCategories = allCategories
                    .stream()
                    .filter(eachCategory -> isValidCategory(category, eachCategory))
                    .collect(Collectors.toList());
            categoryUIModels.add(new CategoryUIModel(category, subCategories));
        }

        mav.addObject("categoryUIModels", categoryUIModels);
        return mav;
    }

    private boolean isValidCategory(ArticleType category, ArticleType eachCategory) {
        return eachCategory.isChild() && eachCategory.getParent().equals(category);
    }
}
