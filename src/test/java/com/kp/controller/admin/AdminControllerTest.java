package com.kp.controller.admin;

import com.kp.domain.User;
import com.kp.domain.model.Role;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CommentService;
import com.kp.service.security.AuthenticationService;
import com.kp.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 09/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController controller;

    @Mock
    private CommentService commentService;

    @Mock
    private ArticleService articleService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void shouldIndex() {
        when(authenticationService.isKpNotAuthenticated()).thenReturn(false);
        final User user = new User(1l, "mail", "pass", "username");
        user.setRole(Role.ADMIN);
        when(authenticationService.getCurrentUser()).thenReturn(user);
        when(commentService.countOfTotalComments()).thenReturn(12l);
        when(articleService.countOfTotalArticles()).thenReturn(345l);
        when(userService.countOfTotalUsers()).thenReturn(50l);

        final ModelAndView mav = controller.index();

        assertEquals("/admin/index", mav.getViewName());
        assertEquals(user, mav.getModel().get("currentUser"));
        assertEquals(12l, mav.getModel().get("totalCommentCount"));
        assertEquals(345l, mav.getModel().get("totalArticleCount"));
        assertEquals(50l, mav.getModel().get("totalUserCount"));
    }
}