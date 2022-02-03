package com.moon.springdata.jpa.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * article 表映射实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-02 22:19
 * @description
 */
@Setter
@Getter
@ToString
@Entity // @Entity 注解用于告诉jpa这是一个实体类，需要把它跟数据库中的表做映射
@Table(name = "article") // 使用 @Table 注解建立了实体类和数据表的关系，name 属性指定映射的表名
public class Article {

    // 标识此属性为主键字段
    @Id
    // 指定主键生成策略，GenerationType.IDENTITY 就是对应到 mysql 中的数据自增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private Integer id;

    /*
     * 使用 @Column 映射类的属性和数据表的字段关系，name 属性指定表中的字段名
     * 当类的属性名和数据表的字段名一致时，此注解可省略
     */
    @Column(name = "author")
    private String author;
    private Date createTime;
    private String title;

    /*
     * @OneToOne 注解声明类间关系，与文章内容 ArticleData 为一对一关系。
     *  mappedBy 属性：一对一关系的对方实体类相应的属性名称，并代表放弃关系维护，由对方来维护关系
     *  cascade 属性：设置级联操作。CascadeType.ALL 代表所有操作都进行级联操作
     */
    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private ArticleData articleData;

    /*
     * @OneToMany 注解声明类间关系，与文章评论 Comment 为一对多关系。
     *  mappedBy 属性：一对多关系的对方实体类相应的属性名称，并代表放弃关系维护，由对方来维护关系
     */
    @OneToMany(mappedBy = "article")
    private Set<Comment> comments = new HashSet<>(0);

    /*
     * @ManyToMany 注解声明类间关系，与文章类型 Type 为多对多关系。
     *  mappedBy 属性：多对多关系的对方实体类相应的属性名称，并代表放弃关系维护，由对方来维护关系
     */
    @ManyToMany(mappedBy = "articles")
    private Set<Type> types = new HashSet<>(0);

}
