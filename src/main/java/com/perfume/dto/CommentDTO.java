package com.perfume.dto;

import java.util.*;

public class CommentDTO extends BaseDTO {
    public String content;

    public String type;

    public Long postId;

    public List<CommentDTO> subComments;
}
