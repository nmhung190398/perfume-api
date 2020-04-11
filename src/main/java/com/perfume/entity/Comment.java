package com.perfume.entity;


import com.nmhung.anotation.QueryField;
import com.nmhung.anotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@TableName
public class Comment extends BaseEntity {

    @Lob
    public String content;

    @QueryField
    public String type;

    @ManyToOne
    @JoinColumn(name = "paren_comment_id")
    public Comment parenComment;

    @OneToMany(mappedBy = "parenComment")
    public List<Comment> subComments;
}




