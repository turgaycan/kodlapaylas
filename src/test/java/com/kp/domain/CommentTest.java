package com.kp.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by tcan on 11/10/15.
 */
public class CommentTest {

    private Comment root;
    private Comment child1;
    private Comment child2;
    private Comment child3;
    private Comment child4;

    @Before
    public void init() {
        root = new Comment(1l, null);
        child1 = new Comment(2l, root);
        child2 = new Comment(3l, child1);
        child3 = new Comment(4l, child2);
        child4 = new Comment(5l, child3);
    }

    @Test
    public void testReplyParentComments() {
        final List<Comment> eplyParentComments = child4.replyParentComments();

        assertThat(eplyParentComments.size(), is(4));
        assertThat(eplyParentComments, hasItems(child1, child2, child3, root));
    }

    @Test
    public void testReplyParentCommentsWithSecondDepth() {
        final List<Comment> replyComments = child2.replyParentComments();

        assertThat(replyComments.size(), is(2));
        assertThat(replyComments, hasItems(child1, root));
    }

}