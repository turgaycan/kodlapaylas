package com.kp.repository;

import com.google.common.collect.Lists;
import com.kp.domain.ArticleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by turgaycan on 9/27/15.
 */
@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan(basePackageClasses = {com.kp.domain.ArticleType.class})
@EnableJpaRepositories(basePackageClasses = {com.kp.repository.ArticleTypeRepository.class})
@EnableAutoConfiguration(exclude = {
        JpaRepositoriesAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@PropertySource(value = "classpath:/application-test.properties")
@ActiveProfiles("test")
public class ArticleTypeRepositoryTest {

    @Autowired
    private ArticleTypeRepository repository;

    @Test
    public void testFindRootArticleTypes() throws Exception {

//        ArticleType articleType1 = new ArticleType(1l, "type1", null);
//        ArticleType articleType12 = new ArticleType(1l, "type1", articleType1);
//
//        repository.save(Lists.newArrayList(articleType1, articleType12));
//        repository.flush();

        List<ArticleType> rootArticleTypes = repository.findRootArticleTypes();

        assertThat(rootArticleTypes.size(), is(1));


    }
}