package com.perfume.repository.custom.impl;

import com.perfume.entity.Comment;
import com.perfume.repository.custom.CommentRepositoryCustom;

public class CommentRepositoryCustomImpl extends BaseRepositoryCustom<Comment> implements CommentRepositoryCustom {
    public CommentRepositoryCustomImpl() {
        super("Cm");
    }
}
