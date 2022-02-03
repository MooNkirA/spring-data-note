package com.moon.springdata.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 文章类型 type 表实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 17:14
 * @description
 */
@Setter
@Getter
@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    private String name;

    /*
     * @ManyToMany 注解声明类间关系，与文章 Article 为多对多关系。由此类来维护关系
     */
    @ManyToMany
    @JoinTable(
            // 代表中间表名称
            name = "article_type",
            // 中间表的外键对应到当前表的主键名称
            joinColumns = {@JoinColumn(name = "tid", referencedColumnName = "tid")},
            // 中间表的外键对应到对方表的主键名称
            inverseJoinColumns = {@JoinColumn(name = "aid", referencedColumnName = "aid")}
    )
    private Set<Article> articles = new HashSet<>(0);

}
