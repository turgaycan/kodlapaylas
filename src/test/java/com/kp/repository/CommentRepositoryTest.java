package com.kp.repository;

import com.kp.config.RepositoryTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by tcan on 05/10/15.
 */
@RunWith(SpringRunner.class)
@RepositoryTest
public class CommentRepositoryTest {

    @Test
    public void testFindByArticleOrderByCreatedateDesc() throws Exception {

    }
}