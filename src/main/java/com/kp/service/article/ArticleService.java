package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.Category;
import com.kp.domain.spec.PageSpec;
import com.kp.dto.DateRange;
import com.kp.repository.ArticleRepository;
import com.kp.service.cache.CacheService;
import com.kp.util.ListUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by turgaycan on 9/26/15.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CacheService cacheService;

    @Transactional
    public Article save(Article article) {
        article.increment();
        final Article updatedArticle = articleRepository.saveAndFlush(article);
        cacheService.set(article.getCacheKey(), updatedArticle);
        return updatedArticle;
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'article-recent'+#article.category.name+ '-'+#recent")
    @Transactional(readOnly = false)
    public List<Article> getRecentArticles(Article article, int recent) {
        final Category category = article.getCategory();

        final Pageable pageable = new PageRequest(0, 10);

        List<Article> recentArticles = articleRepository.findByCategoryPageable(category.getId(), pageable).getContent();

        final Collection<Article> elements = CollectionUtils.select(recentArticles, each -> each != article);

        return ListUtil.defensiveArrayList(newArrayList(elements), recent);
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'related-'+#article.id+ '-'+#recent")
    @Transactional(readOnly = true)
    public List<Article> getRelatedArticles(List<Article> recentArticles, Article article, int recent) {
        final Pageable pageable = PageSpec.buildPageSpecificationByFieldDesc(0, 10, "createdate");

        List<Article> relatedArticles = articleRepository.findByCategoryOrTitleLike(article.getCategory(), article.getTitle(), pageable).getContent();

        List<Article> subtractList = getSubtractArticles(recentArticles, article);
        relatedArticles = ListUtils.subtract(relatedArticles, subtractList);
        return ListUtil.defensiveArrayList(relatedArticles, recent);
    }

    private List<Article> getSubtractArticles(List<Article> recentArticles, Article article) {
        List<Article> subtractList = newArrayList();
        subtractList.addAll(recentArticles);
        subtractList.add(article);
        return subtractList;
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'category'+ #category.name + '-' + #pageNum + '-' + #size")
    @Transactional(readOnly = true)
    public Page<Article> getByCategory(Category category, int pageNum, int size) {
        return articleRepository.findByCategory(category,
                PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'categories' + #categories.get(0).name + '-' + #pageNum+ '-' +#size")
    @Transactional(readOnly = true)
    public Page<Article> getByCategoryIn(List<Category> categories, int pageNum, int size) {
        return articleRepository.findByCategoryIn(categories,
                PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'dateRange-' + #dateRange.startDate + '-' +#dateRange.endDate + '-' +#pageNum + '-' + #size")
    @Transactional(readOnly = true)
    public Page<Article> getByCreatedateAfterAndCreatedateBefore(DateRange dateRange, int pageNum, int size) {
        return articleRepository.findByCreatedateAfterAndCreatedateBefore(
                dateRange.getStartDate(),
                dateRange.getEndDate(),
                PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'article-'+#articleId")
    @Transactional(readOnly = true)
    public Article getById(Long articleId) {
        return articleRepository.findById(articleId);
    }

    @Transactional(readOnly = true)
    public Article getOne(Long id) {
        return articleRepository.findOne(id);
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'featureArticles'")
    @Transactional(readOnly = true)
    public List<Article> getFeatureArticles() {
        Pageable topTen = PageSpec.buildPageSpecificationByFieldDesc(0, 10, "viewNumber");
        return articleRepository.findPageableOrderByCreatedateDesc(topTen).getContent();
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'lastOne'")
    @Transactional(readOnly = true)
    public Article getLastOne() {
        Pageable lastOne = PageSpec.buildPageSpecificationByFieldDesc(0, 1, "createdate");
        Article article = articleRepository.findPageableOrderByCreatedateDesc(lastOne).getContent().get(0);
        long commentCount = commentService.getArticleCommentCount(article);
        article.setCommentListSize(commentCount);
        return article;
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'category-' + #articleTypeParentId")
    @Transactional(readOnly = true)
    public List<Article> getByCategoryOrderByViewNumber(Long articleTypeParentId) {
        List<Category> categories = categoryService.getByParentId(articleTypeParentId);
        Long[] articleTypeIds = new Long[categories.size()];
        int index = 0;
        for (Category category : categories) {
            if (category.isChildCategory()) {
                articleTypeIds[index++] = category.getId();
            }
        }

        return articleRepository.findByCategoryId(articleTypeIds);
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'pageable-' + #pageNum + '-' + #size", unless = "#result == null")
    public Page<Article> getArticlesAsPageable(int pageNum, int size) {
        return getArticlesAsPageableNotCacheAble(pageNum, size);
    }

    @Transactional(readOnly = true)
    public Page<Article> getArticlesAsPageableNotCacheAble(int pageNum, int size) {
        final Pageable page = PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate");
        return articleRepository.findPageableOrderByCreatedateDesc(page);
    }

    @Cacheable(cacheNames = "kpCache", value = "kpCache", key = "'tag-' + #tag + '-' + #pageNum + '-' + #size")
    @Transactional(readOnly = true)
    public Page<Article> getArticlesByTagAsPageable(String tag, int pageNum, int size) {
        final Pageable page = PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate");
        return articleRepository.findByTagsContaining(tag, page);
    }

    @Transactional(readOnly = true)
    public long countOfTotalArticles() {
        return articleRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Article> getAll() {
        return articleRepository.findAll();
    }
}
