package com.kp.controller;

import com.kp.service.article.ArticleService;
import com.kp.service.article.ArticleTypeService;
import com.kp.service.article.CommentService;
import com.kp.service.article.TagService;
import com.kp.service.seo.SeoMetaDataService;
import com.kp.util.DateUtils;
import org.mockito.Mock;

/**
 * Created by tcan on 18/09/16.
 */
public class CommonControllerTest {

    @Mock
    protected ArticleService articleService;

    @Mock
    protected ArticleTypeService articleTypeService;

    @Mock
    protected CommentService commentService;

    @Mock
    protected TagService tagService;

    @Mock
    private SeoMetaDataService seoMetaDataService;

    @Mock
    protected DateUtils dateUtils;
}
