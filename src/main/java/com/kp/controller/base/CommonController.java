package com.kp.controller.base;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.domain.Tag;
import com.kp.dto.CategoryUIModel;
import com.kp.service.article.ArticleService;
import com.kp.service.article.ArticleTypeService;
import com.kp.service.article.CommentService;
import com.kp.service.article.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tcan on 16/10/15.
 */
public abstract class CommonController {

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected ArticleTypeService articleTypeService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected TagService tagService;

    protected void populateCommonsForArticle(ModelAndView mav, Article article) {
        final List<Article> recentArticles = articleService.findRecentArticles(article, Article.RECENT_ARTICLE_LIMIT);
        final List<Article> relatedArticles = articleService.findRelatedArticles(recentArticles, article, Article.RECENT_ARTICLE_LIMIT);
        mav.addObject("relatedArticles", relatedArticles);
        mav.addObject("commentBaseModel", commentService.buildCommentModel(article));
        populateTags(mav, tagsByArticleType(article.getArticleType()));
    }

    protected List<Tag> tagsByArticleType(ArticleType articleType) {
        return tagService.findByArticleType(articleType);
    }

    private List<ArticleType> getAllRootTypes() {
        return articleTypeService.findAllRootTypes();
    }

    protected void populateCommons(ModelAndView mav) {
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
        List<Article> articles = (List<Article>) mav.getModel().get("pageArticles");
        populateTags(mav, tagsByArticleType(articles.get(0).getArticleType()));
    }

    private void populateTags(ModelAndView mav, List<Tag> attributeValue) {
        mav.addObject("tags", attributeValue);
    }

    private boolean isValidCategory(ArticleType category, ArticleType eachCategory) {
        return eachCategory.isChildCategory() && eachCategory.getParent().equals(category);
    }
}
