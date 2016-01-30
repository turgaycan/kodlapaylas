package com.kp.service.article;

import com.google.common.collect.Lists;
import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.domain.spec.PageSpec;
import com.kp.dto.DateRange;
import com.kp.repository.ArticleRepository;
import com.kp.util.DateUtils;
import com.kp.util.ListUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/26/15.
 */
@Service
public class ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTypeService articleTypeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DateUtils dateUtils;

    @Transactional(readOnly = true)
    public Optional<Article> findArticleById(Long id) {
        return Optional.ofNullable(articleRepository.findArticleById(id));
    }

    //Caching..
    @Transactional(readOnly = false)
    public List<Article> findRecentArticles(Article article, int recent) {
        List<Article> recentArticles = articleRepository.
                findByArticleTypeOrderByCreatedateDesc(article.getArticleType(),
                        PageSpec.buildPageSpecificationByFieldDesc(0, 10, "createdate")).getContent();
        return ListUtil.defensiveSubList(
                Lists.newArrayList(CollectionUtils.select(recentArticles, each -> each != article))
                , recent);
    }

    //Caching..
    @Transactional(readOnly = true)
    public List<Article> findRelatedArticles(List<Article> recentArticles, Article article, int recent) {
        List<Article> relatedArticles = articleRepository.
                findByArticleTypeOrTitleLike(article.getArticleType(), article.getTags(),
                        PageSpec.buildPageSpecificationByFieldDesc(0, 10, "createdate")).getContent();
        List<Article> subtractList = getSubtractArticles(recentArticles, article);
        relatedArticles = ListUtils.subtract(relatedArticles, subtractList);
        return ListUtil.defensiveSubList(relatedArticles, recent);
    }

    private List<Article> getSubtractArticles(List<Article> recentArticles, Article article) {
        List<Article> subtractList = Lists.newArrayList();
        subtractList.addAll(recentArticles);
        subtractList.add(article);
        return subtractList;
    }

    @Transactional(readOnly = true)
    public Page<Article> findByArticleType(ArticleType articleType, int pageNum, int size) {
        return articleRepository.findByArticleType(articleType,
                PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }


    @Transactional(readOnly = true)
    public Page<Article> findByArticleTypeIn(List<ArticleType> articleTypes, int pageNum, int size) {
        return articleRepository.findByArticleTypeIn(articleTypes,
                PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }

    @Transactional(readOnly = true)
    public Page<Article> findByCreatedateAfterAndCreatedateBefore(DateRange dateRange, int pageNum, int size) {
        return articleRepository.findByCreatedateAfterAndCreatedateBefore(
                dateRange.getStartDate(),
                dateRange.getEndDate(),
                PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }

    @Transactional(readOnly = true)
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleRepository.findById(id));
    }

    @Transactional(readOnly = true)
    public Article findBy(Long id) {
        return articleRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Article> findFeatureArticles() {
        Pageable topTen = PageSpec.buildPageSpecificationByFieldDesc(0, 10, "viewNumber");
        return articleRepository.findAll(topTen).getContent();
    }

    //TODO turgay : caching..
    @Transactional(readOnly = true)
    public Article findLastOne() {
        Pageable lastOne = PageSpec.buildPageSpecificationByFieldDesc(0, 1, "id");
        Article article = articleRepository.findAll(lastOne).getContent().get(0);
        long commentCount = commentService.getArticleCommentCount(article);
        article.setCommentListSize(commentCount);
        return article;
    }

    //TODO turgay: caching.. (ONE_DAY)
    @Transactional(readOnly = true)
    public List<Article> findByArticleTypeOrderByViewNumber(Long articleTypeParentId) {
        List<ArticleType> articleTypes = articleTypeService.findByParentId(articleTypeParentId);
        Long[] articleTypeIds = new Long[articleTypes.size()];
        int index = 0;
        for (ArticleType articleType : articleTypes) {
            if (articleType.isChildCategory()) {
                articleTypeIds[index++] = articleType.getId();
            }
        }

        return articleRepository.findByArticleTypeId(articleTypeIds);
    }

    //TODO turgay : cacheable..(ONE_DAY)
    @Transactional(readOnly = true)
    public Page<Article> findArticlesAsPageable(int pageNum, int size) {
        return articleRepository.OrderByCreatedateDesc(PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate"));
    }

}
