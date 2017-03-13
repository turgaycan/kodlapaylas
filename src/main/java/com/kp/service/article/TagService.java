package com.kp.service.article;

import com.kp.domain.Category;
import com.kp.domain.Tag;
import com.kp.domain.spec.PageSpec;
import com.kp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tcan on 17/10/15.
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Cacheable(value = "kpCache", key = "'tags-'.concat(#category.name)")
    @Transactional(readOnly = true)
    public List<Tag> getByCategory(Category category) {
        final Category rootCategory = category.getRoot();
        return tagRepository.findByCategory(rootCategory,
                PageSpec.buildPageSpecificationByFieldDesc(1, 15, "count")).getContent();
    }

    @Cacheable(value = "kpCache", key = "'tags-count'")
    @Transactional(readOnly = true)
    public List<Tag> getByOrderCountDesc() {
        return tagRepository.orderByCountDesc(PageSpec.buildPageSpecificationByFieldDesc(0, 15, "count"));
    }

}
