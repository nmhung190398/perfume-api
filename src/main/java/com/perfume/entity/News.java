package com.perfume.entity;


import com.nmhung.anotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@TableName
public class News extends BaseEntity{

    @Column(columnDefinition="TEXT")
    private String title;
    @Column(columnDefinition="TEXT")
    private String content;

}
