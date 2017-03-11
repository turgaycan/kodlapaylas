package com.kp.controller.base;

import com.kp.domain.Article;
import com.kp.domain.Category;
import com.kp.domain.Tag;
import com.kp.dto.CategoryUIModel;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CategoryService;
import com.kp.service.article.CommentService;
import com.kp.service.article.TagService;
import com.kp.service.seo.SeoMetaDataService;
import com.kp.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tcan on 16/10/15.
 */
public abstract class CommonController {

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected TagService tagService;

    @Autowired
    private SeoMetaDataService seoMetaDataService;

    @Autowired
    protected DateUtils dateUtils;

    protected abstract List<Integer> addArchiveYearsToMav();

    protected void populateCommonsForArticle(ModelAndView mav, Article article) throws IOException {
        final List<Article> recentArticles = articleService.getRecentArticles(article, Article.RECENT_ARTICLE_LIMIT);
        final List<Article> relatedArticles = articleService.getRelatedArticles(recentArticles, article, Article.RECENT_ARTICLE_LIMIT);
        mav.addObject("relatedArticles", relatedArticles);
        mav.addObject("commentBaseModel", commentService.buildCommentModel(article));
        mav.addObject("seoMetaData", seoMetaDataService.buildPageSeoMetaData(mav.getViewName(), buildPropertyMap(article)));
        populateTags(mav, tagsByArticleType(article.getCategory()));
    }

    private Map<String, String[]> buildPropertyMap(Article article) {
        Map<String, String[]> propertyMap = new HashMap<>();
        propertyMap.put(SeoMetaDataService.TITLE, new String[]{article.getTitle()});
        propertyMap.put(SeoMetaDataService.KEYWORDS, new String[]{article.getTags()});
        propertyMap.put(SeoMetaDataService.DESCRIPTION, new String[]{article.getTitle()});

        return propertyMap;
    }

    protected void addSeoMetaDataToMav(ModelAndView mav) throws IOException {
        mav.addObject("seoMetaData", seoMetaDataService.buildPageSeoMetaData(mav.getViewName()));
    }

    protected void addArchiveYearsToMav(ModelAndView mav) {
        mav.addObject("years", addArchiveYearsToMav());
    }


    protected List<Tag> tagsByArticleType(Category category) {
        return tagService.getByArticleType(category);
    }

    private List<Category> getAllRootTypes() {
        return categoryService.getAllRootTypes();
    }

    protected void populateCommons(ModelAndView mav) {
        List<CategoryUIModel> categoryUIModels = new ArrayList<>();
        final List<Category> allCategories = categoryService.getAll();
        for (Category category : getAllRootTypes()) {
            List<Category> subCategories = allCategories
                    .stream()
                    .filter(eachCategory -> isValidCategory(category, eachCategory))
                    .collect(Collectors.toList());
            categoryUIModels.add(new CategoryUIModel(category, subCategories));
        }

        mav.addObject("categoryUIModels", categoryUIModels);
        List<Article> articles = (List<Article>) mav.getModel().get("pageArticles");
        if (articles.isEmpty()) {
            populateTags(mav, new ArrayList<>());
            return;
        }
        populateTags(mav, tagsByArticleType(articles.get(0).getCategory()));
    }

    private void populateTags(ModelAndView mav, List<Tag> attributeValue) {
        mav.addObject("tags", attributeValue);
    }

    private boolean isValidCategory(Category category, Category eachCategory) {
        return eachCategory.isChildCategory() && eachCategory.getParent().equals(category);
    }
}
