package com.kp.service.article;

import com.kp.domain.ArticleType;
import com.kp.domain.Tag;
import com.kp.domain.spec.PageSpec;
import com.kp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(readOnly = true)
    public List<Tag> findByArticleType(ArticleType articleType) {
        final ArticleType rootArticleType = articleType.getRoot();
        return tagRepository.findByArticleType(rootArticleType,
                PageSpec.buildPageSpecificationByFieldDesc(1, 15, "count")).getContent();
    }

    @Transactional(readOnly = true)
    public List<Tag> findByOrderCountDesc() {
        return tagRepository.OrderByCountDesc(PageSpec.buildPageSpecificationByFieldDesc(0, 15, "count"));
    }

}
