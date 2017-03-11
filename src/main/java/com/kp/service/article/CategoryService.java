package com.kp.service.article;

import com.kp.domain.Category;
import com.kp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by turgaycan on 9/27/15.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable(value = "kpCache", key = "'allRootTypes'")
    @Transactional(readOnly = true)
    public List<Category> getAllRootTypes() {
        return categoryRepository.findRootArticleTypes();
    }

    @Cacheable(value = "kpCache", key = "'articleType-'.concat(#name)", condition = "#this != null")
    @Transactional(readOnly = true)
    public Category getByName(String name) {
        return categoryRepository.findByName(name).get();
    }

    @Cacheable(value = "kpCache", key = "'allCategories'")
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Cacheable(value = "kpCache", key = "'articleType-'.concat(#parentId)")
    @Transactional(readOnly = true)
    public List<Category> getByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId);
    }
}
