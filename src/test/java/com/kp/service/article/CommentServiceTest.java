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
    private Comment child1;
    private Comment child2;
    private Comment child3;
    private Comment child4;

    @Before
    public void init() {
        article = new Article();
        root = new Comment(1l, null, article);
        child1 = new Comment(2l, root, article);
        child2 = new Comment(3l, child1, article);
        child3 = new Comment(4l, child2, article);
        child4 = new Comment(5l, child3, article);
        when(commentRepository.findByParent(root)).thenReturn(child1);
        when(commentRepository.findByParent(child1)).thenReturn(child2);
        when(commentRepository.findByParent(child2)).thenReturn(child3);
        when(commentRepository.findByParent(child3)).thenReturn(child4);
        when(commentRepository.findByParent(child4)).thenReturn(null);
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
        copyOnWriteArrayList.addAll(newArrayList(root, child1, child2, child3, child4));
        final List<Comment> commentList = service.replyChildComments(child2, newArrayList(), copyOnWriteArrayList, newArrayList());

        assertThat(commentList.size(), is(2));
        assertThat(commentList, hasItems(child3, child4));
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