package com.kp.controller;

import com.kp.domain.*;
import com.kp.dto.CommentBaseModel;
import com.kp.dto.CommentUIModel;
import com.kp.dto.MonthUIModel;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CategoryService;
import com.kp.service.article.CommentService;
import com.kp.service.article.TagService;
import com.kp.service.seo.SeoMetaDataService;
import com.kp.util.DateUtils;
import org.junit.Before;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tcan on 18/09/16.
 */
public class CommonControllerTest {

    @Mock
    protected ArticleService articleService;

    @Mock
    protected CategoryService categoryService;

    @Mock
    protected CommentService commentService;

    @Mock
    protected TagService tagService;

    @Mock
    protected SeoMetaDataService seoMetaDataService;

    @Mock
    protected DateUtils dateUtils;

    protected List<Category> categoryList = new ArrayList<>();
    protected List<Article> articleList = new ArrayList<>();
    protected List<Tag> tagList = new ArrayList<>();
    protected List<MonthUIModel> monthUIModels = new ArrayList<>();
    protected List<Comment> commentList = new ArrayList<>();
    protected List<CommentUIModel> commentUIModelList = new ArrayList<>();
    protected CommentBaseModel commentBaseModel = new CommentBaseModel();

    @Before
    public void init() {
        for (long index = 1; index <= 5; index++) {
            Category category = new Category(index, "cat" + index, null);
            categoryList.add(category);
            final Article article = new Article(index, "content" + index, "title" + index, "tags" + index, new Date(), category, new User());
            articleList.add(article);
            final Tag tag = new Tag(index, 3 + Integer.valueOf((int) index));
            tagList.add(tag);
        }
        for (int index = 0; index < 12; index++) {
            monthUIModels.add(new MonthUIModel(index, "month" + index));
        }

        for (long index = 1; index <= 5; index++) {
            for (long innerIndex = 1; innerIndex <= 5; innerIndex++) {
                commentList.add(new Comment(innerIndex, null));
            }
            commentUIModelList.add(new CommentUIModel(commentList.get(0), commentList));
        }
        commentBaseModel = new CommentBaseModel(5, commentUIModelList);
    }
}
