package com.moon.springdata.jpa.dao;

import com.moon.springdata.jpa.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * article 表持久层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-02 22:29
 * @description
 */
/*
 * 在 Spring Data JPA 中定义的dao接口，需要继承以下接口：
 * `JpaRepository<实体类类型，主键类型>`：用来完成基本 CRUD 操作
 * [非必须] `JpaSpecificationExecutor<实体类类型>`：用于复杂查询（分页等查询操作）
 */
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    // 根据标题查询
    List<Article> findByTitle(String title);

    // 根据标题模糊查询
    List<Article> findByTitleLike(String title);

    // 根据标题和作者查询
    List<Article> findByTitleAndAuthor(String title, String author);

    // 根据ID范围查询 小于
    List<Article> findByIdIsLessThan(Integer aid);

    // 根据ID范围查询 between in
    List<Article> findByIdBetween(Integer startAid, Integer endAid);

    // 根据ID范围查询 in
    List<Article> findByIdIn(List<Integer> aids);

    // 根据创建时间之后查询
    List<Article> findByCreateTimeAfter(Date createTime);

    /*
     * JPQL 语句是 JPA 中定义的一种查询语言。类似于SQL语句，但是要使用实体类名代替表名，使用属性名代替字段名[面向对象查询]
     */
    // 按展示位置参数绑定，占位符从1开始，按照位置替换 title 和 author 查询
    @Query("from Article a where a.title = ?1 and a.author =?2")
    List<Article> findByCondition1(String title, String author);

    // 按名字参数绑定
    @Query("from Article a where a.title = :title and a.author = :authors")
    List<Article> findByCondition2(@Param("title") String title, @Param("authors") String author);

    // like模糊查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition3(@Param("title") String title);

    // 排序查询
    @Query("from Article a where a.title like %:title% order by a.id desc ")
    List<Article> findByCondition4(@Param("title") String title);

    // 分页查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition5(Pageable pageable, @Param("title") String title);

    // 传入集合参数查询
    @Query("from Article a where a.id in :aids")
    List<Article> findByCondition6(@Param("aids") List<Integer> aids);

    // 传入Bean进行查询（SPEL表达式查询）
    @Query("from Article a where a.title = :#{#article.title} and a.author = :#{#article.author}")
    List<Article> findByCondition7(@Param("article") Article article);

    // 本地SQL查询
    @Query(value = "select * from article a where a.title = ?1 and a.author =?2", nativeQuery = true)
    List<Article> findByNativeSql(String title, String author);

}
