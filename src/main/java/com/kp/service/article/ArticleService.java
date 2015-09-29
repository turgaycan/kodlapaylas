package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.repository.ArticleRepository;
import com.kp.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/26/15.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Optional<Article> findArticleById(Long id) {
        return Optional.ofNullable(articleRepository.findArticleById(id));
    }

    @Transactional(readOnly = false)
    public List<Article> findRecentArticles(int recent) {
//        Page<Article> recentArticles = articleRepository.findAll(new PageRequest(0, recent, new Sort(
//                new Sort.Order(Sort.Direction.DESC, "createdate")
//        )));
//        return Lists.newArrayList(recentArticles);
        return ListUtil.defensiveSubList(articleRepository.findRecentArticles(), recent);
    }

    @Transactional(readOnly = true)
    public List<Article> findByArticleType(ArticleType articleType, int pageNum, int size) {
        return articleRepository.findArticlesByArticleType(articleType, new PageRequest(pageNum, size, new Sort(
                new Sort.Order(Sort.Direction.DESC, "createdate"))));
    }

}
