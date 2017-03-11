package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.domain.spec.PageSpec;
import com.kp.dto.CommentBaseModel;
import com.kp.dto.CommentUIModel;
import com.kp.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
        article = new Article(1l);
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
    public void shouldSave() {
        when(commentRepository.save(root)).thenReturn(root);
        final Comment persistedOne = service.save(root);

        assertNotNull(persistedOne);
        assertEquals(Long.valueOf(1), persistedOne.getId());
    }

    @Test
    public void shouldGetPaginated() {
        when(commentRepository.findAll(PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(newArrayList(reply1, reply2, reply3)));
        final Page<Comment> comments = service.getPaginated(1, 10);

        assertEquals(3, comments.getTotalElements());
        assertThat(comments, hasItems(reply1, reply2, reply3));
    }


    @Test
    public void shouldGetCommentsByArticleId() {
        when(commentRepository.findByArticleIdOrderByCreatedateDesc(1l)).thenReturn(newArrayList(root, reply1, reply2, reply3));
        final List<Comment> comments = service.getCommentsByArticleId(1l);

        assertEquals(4, comments.size());
        assertThat(comments, hasItems(root, reply1, reply2, reply3));
    }

    @Test
    public void shouldBuildCommentModel() {
        Article article2 = new Article(2l);
        Comment comment5 = new Comment(5l, null, article2);
        Comment comment6 = new Comment(6l, null, article2);
        Comment comment7 = new Comment(7l, comment6, article2);
        when(commentRepository.findByArticleIdOrderByCreatedateDesc(2l)).thenReturn(newArrayList(comment5, comment6, comment7));
        when(commentRepository.findByParent(comment5)).thenReturn(null);
        when(commentRepository.findByParent(comment6)).thenReturn(comment7);

        CommentBaseModel commentBaseModel = service.buildCommentModel(article2);

        final List<CommentUIModel> commentUIModels = commentBaseModel.getCommentUIModels();

        assertThat(commentUIModels.size(), is(3));
        assertThat(commentUIModels.get(0).getComment(), is(comment5));
        assertThat(commentUIModels.get(1).getComment(), is(comment6));
        assertThat(commentUIModels.get(2).getComment(), is(comment7));
    }

    @Test
    public void shouldGetArticleCommentCount() {
        when(commentRepository.getArticleCount(article)).thenReturn(3l);
        final long commentCount = service.getArticleCommentCount(article);

        assertEquals(3, commentCount);
    }

    @Test
    public void shouldCountOfTotalComments() {
        when(commentRepository.count()).thenReturn(12l);
        final long totalCommentsCount = service.countOfTotalComments();

        assertEquals(12, totalCommentsCount);
    }

    @Test
    public void shouldGetOne() {
        when(commentRepository.findOne(5l)).thenReturn(reply4);
        final Comment comment = service.getOne(5l);

        assertEquals(reply3, comment.getParent());
    }

    @Test
    public void shouldGetAll() {
        when(commentRepository.findAll()).thenReturn(newArrayList(root, reply1, reply2, reply3, reply4));
        final List<Comment> commentList = service.getAll();
        assertEquals(5, commentList.size());
    }

}