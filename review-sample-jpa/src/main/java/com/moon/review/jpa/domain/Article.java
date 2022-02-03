package com.moon.review.jpa.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * article 表映射实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-02 11:09
 * @description
 */
@Data
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
}
