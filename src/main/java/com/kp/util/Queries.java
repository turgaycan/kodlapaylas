package com.kp.util;

/**
 * Created by tcan on 03/01/16.
 */
public final class Queries {

    private Queries() {
    }

    public static String COMMENT_QUERY = String.format("WITH RECURSIVE comment_tree(id, parent_id, path) AS ( SELECT id, parent_id, ARRAY[id]  FROM kp.comment c" +
            "  WHERE parent_id IS NULL and article_id= (:articleId) " +
            "  UNION ALL " +
            "  SELECT c.id, c.parent_id, path || c.id " +
            "  FROM comment_tree ct " +
            "    JOIN kp.comment c ON c.parent_id=ct.id" +
            "  WHERE NOT c.id = ANY(path)) " +
            "SELECT id, parent_id as parentId, path FROM comment_tree  ORDER BY path");

}
