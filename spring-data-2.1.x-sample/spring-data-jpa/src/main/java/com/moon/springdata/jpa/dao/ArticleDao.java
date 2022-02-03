package com.moon.springdata.jpa.dao;

import com.moon.springdata.jpa.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
}
