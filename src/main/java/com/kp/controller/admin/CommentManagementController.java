package com.kp.controller.admin;

import com.kp.domain.Comment;
import com.kp.service.article.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tcan on 05/02/17.
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class CommentManagementController {

    private static final int DEFAULT_PAGEINDEX = 1;
    private static final int DEFAULT_PAGESIZE = 10;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"/comments"}, method = RequestMethod.GET)
    public ModelAndView list() {
        final List<Comment> comments = commentService.getAll();
        ModelAndView mav = new ModelAndView("/admin/comments");
        mav.addObject("comments", comments);
        return mav;
    }

    @RequestMapping(value = {"/comments/{pageIndex:\\d+$}/{pageSize:\\d+$}"}, method = RequestMethod.GET)
    public ModelAndView list(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {

        if (pageIndex <= 0) {
            pageIndex = DEFAULT_PAGEINDEX;
        }
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGESIZE;
        }

        final Page<Comment> comments = commentService.getPaginated(pageIndex - 1, pageSize);
        ModelAndView mav = new ModelAndView("/admin/comments");
        mav.addObject("comments", comments.getContent());
        return mav;
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ModelAndView listOneById(Long id) {
        final Comment comment = commentService.findById(id).get();
        ModelAndView mav = new ModelAndView("/admin/comment");
        mav.addObject("comments", comment);
        return mav;
    }
}
