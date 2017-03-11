package com.kp.controller.admin;

import com.kp.domain.Comment;
import com.kp.service.article.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 11/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentManagementControllerTest {

    @InjectMocks
    private CommentManagementController controller;

    @Mock
    private CommentService commentService;

    private List<Comment> commentList = new ArrayList<>();

    @Before
    public void init() {
        for (long index = 1; index < 5l; index++) {
            final Comment comment = new Comment(index, null);
            commentList.add(comment);
        }
    }

    @Test
    public void shouldListComments() {
        when(commentService.getAll()).thenReturn(commentList);

        final ModelAndView mav = controller.list();

        assertEquals("/admin/comments", mav.getViewName());
        assertEquals(commentList, mav.getModel().get("comments"));
    }


    @Test
    public void shouldListByPageable() {
        when(commentService.getPaginated(0, 10)).thenReturn(new PageImpl<>(commentList));

        final ModelAndView mav = controller.list(1, 10);

        assertEquals("/admin/comments", mav.getViewName());
        assertEquals(commentList, mav.getModel().get("comments"));
    }

    @Test
    public void shouldListOneById() {
        final Comment comment = new Comment(1l, null);
        when(commentService.getOne(1l)).thenReturn(comment);
        final ModelAndView mav = controller.listOneById(1l);

        assertEquals("/admin/comment", mav.getViewName());
        assertEquals(comment, mav.getModel().get("comment"));
    }
}