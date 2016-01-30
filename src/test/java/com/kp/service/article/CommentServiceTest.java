package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.dto.CommentBaseModel;
import com.kp.dto.CommentUIModel;
import com.kp.repository.CommentRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.google.common.collect.Lists.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 05/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService service;

    @Mock
    private CommentRepository commentRepository;

    private Article article;
    private Comment root;
    private Comment reply1;
    private Comment reply2;
    private Comment reply3;
    private Comment reply4;

    @Before
    public void init() {
        article = new Article();
        root = new Comment(1l, null, article);
        reply1 = new Comment(2l, root, article);
        reply2 = new Comment(3l, reply1, article);
        reply3 = new Comment(4l, reply2, article);
        reply4 = new Comment(5l, reply3, article);
        when(commentRepository.findByParent(root)).thenReturn(reply1);
        when(commentRepository.findByParent(reply1)).thenReturn(reply2);
        when(commentRepository.findByParent(reply2)).thenReturn(reply3);
        when(commentRepository.findByParent(reply3)).thenReturn(reply4);
        when(commentRepository.findByParent(reply4)).thenReturn(null);
    }

    @Test
    public void testSave() {

    }

    @Test
    public void testFindComments() {

    }

    @Test
    public void testFindReplyChildComments() {
        final CopyOnWriteArrayList<Comment> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(newArrayList(root, reply1, reply2, reply3, reply4));
//        final List<Comment> commentList = service.replyChildComments(reply2, newArrayList(), copyOnWriteArrayList, newArrayList());

//        assertThat(commentList.size(), is(2));
//        assertThat(commentList, hasItems(reply3, reply4));
    }

    @Ignore
    @Test
    public void testBuildCommentModel() {
        Article article2 = new Article();
        Comment comment5 = new Comment(5l, null, article2);
        Comment comment6 = new Comment(6l, null, article2);
        Comment comment7 = new Comment(7l, comment6, article2);
        when(commentRepository.findByArticleOrderByCreatedateDesc(article2)).thenReturn(newArrayList(comment5, comment6, comment7));
        when(commentRepository.findByParent(comment5)).thenReturn(null);
        when(commentRepository.findByParent(comment6)).thenReturn(comment7);

        CommentBaseModel commentBaseModel = service.buildCommentModel(article2);

        final List<CommentUIModel> commentUIModels = commentBaseModel.getCommentUIModels();

        assertThat(commentUIModels.size(), is(2));
        assertThat(commentUIModels.get(0).getComment(), is(comment5));
        assertThat(commentUIModels.get(1).getComment(), is(comment6));
        assertThat(commentUIModels.get(1).getReplyComments(), hasItems(comment7));
    }
}