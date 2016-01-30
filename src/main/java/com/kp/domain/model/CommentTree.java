package com.kp.domain.model;


import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by tcan on 05/12/15.
 */
public class CommentTree implements Serializable {
    private static final long serialVersionUID = 7028741782031680871L;

    private Long id;
    private Long parentId;
    private Object path;

    public CommentTree() {
    }

    public CommentTree(Long id, Long parentId, Object path) {
        this.id = id;
        this.parentId = parentId;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Object getPath() {
        return path;
    }

    public void setPath(Object path) {
        this.path = path;
    }

    public List<CommentTree> childCommentTrees(List<CommentTree> commentTreeList) {
        List<CommentTree> filteredList = newArrayList();
        for (CommentTree commentTree : commentTreeList) {
            if (parentId != null && String.valueOf(commentTree.path).startsWith(id.toString())) {
                filteredList.add(commentTree);
            }
        }

        return filteredList;
    }
}
