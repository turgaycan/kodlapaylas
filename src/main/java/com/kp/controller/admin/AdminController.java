package com.kp.controller.admin;

import com.kp.controller.base.BaseUserController;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CommentService;
import com.kp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tcan on 04/02/17.
 */
@Controller
public class AdminController extends BaseUserController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = {"/admin", "/admin/index"})
    public ModelAndView index() {
        final ModelAndView mav = super.index("/admin/index");
        mav.addObject("totalCommentCount", commentService.countOfTotalComments());
        mav.addObject("totalArticleCount", articleService.countOfTotalArticles());
        mav.addObject("totalUserCount", userService.countOfTotalUsers());
        return mav;
    }
}
