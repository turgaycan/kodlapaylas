package com.kp.service.article;

import com.kp.domain.ArticleType;
import com.kp.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by turgaycan on 9/27/15.
 */
@Service
public class ArticleTypeService {

    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    @Cacheable(value = "kpCache", key = "'allRootTypes'")
    @Transactional(readOnly = true)
    public List<ArticleType> findAllRootTypes() {
        return articleTypeRepository.findRootArticleTypes();
    }

    @Cacheable(value = "kpCache", key = "'articleType-'.concat(#name)", condition = "#this != null")
    @Transactional(readOnly = true)
    public ArticleType findByName(String name) {
        return articleTypeRepository.findByName(name).get();
    }

    @Cacheable(value = "kpCache", key = "'allCategories'")
    @Transactional(readOnly = true)
    public List<ArticleType> findAll() {
        return articleTypeRepository.findAll();
    }

    @Cacheable(value = "kpCache", key = "'articleType-'.concat(#parentId)")
    @Transactional(readOnly = true)
    public List<ArticleType> findByParentId(Long parentId) {
        return articleTypeRepository.findByParentId(parentId);
    }
}
