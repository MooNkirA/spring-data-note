package com.moon.springdata.jpa.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 文章评论 comment 表实体类
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 16:19
 * @description
 */
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String comment;

    /*
     * @ManyToOne 注解声明类间关系，与文章 Article 为多对一关系。由此类来维护关系
     */
    @ManyToOne()
    /*
     * @JoinColumn 注解声明维护外键关系，当前表中的外键 articleId 指向 Article 表的主键 id
     *  name 属性：当前表中的外键名
     *  referencedColumnName 属性：指向的对方表中的主键名
     */
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    private Article article;

}
