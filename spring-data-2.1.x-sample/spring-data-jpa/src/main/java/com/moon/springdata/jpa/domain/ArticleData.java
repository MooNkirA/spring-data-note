package com.moon.springdata.jpa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 文章详情表 article_data 实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 15:43
 * @description
 */
@Data
@Entity
@Table(name = "article_data")
public class ArticleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    /*
     * @OneToOne 注解声明类间关系，与文章内容 ArticleData 为一对一关系。由此类来维护关系
     */
    @OneToOne
    /*
     * @JoinColumn 注解声明维护外键关系，当前表中的外键 articleId 指向 Article 表的主键 id
     *  name 属性：当前表中的外键名
     *  referencedColumnName 属性：指向的对方表中的主键名
     *  unique 属性：代表外键是否唯一，默认值是 false：不唯一；true：唯一。（因为一对一关系所以需要设置为 true）
     */
    @JoinColumn(name = "articleId", referencedColumnName = "aid", unique = true)
    private Article article;

}
