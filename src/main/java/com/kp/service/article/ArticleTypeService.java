package com.kp.service.article;

import com.kp.domain.ArticleType;
import com.kp.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/27/15.
 */
@Service
public class ArticleTypeService {

    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    @Transactional(readOnly = true)
    public List<ArticleType> findAllRootTypes() {
        return articleTypeRepository.findRootArticleTypes();
    }

    @Transactional(readOnly = true)
    public Optional<ArticleType> findByName(String name){
        return articleTypeRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<ArticleType> findAll(){
        return articleTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ArticleType> findByParentId(Long parentId){
        return articleTypeRepository.findByParentId(parentId);
    }
}
