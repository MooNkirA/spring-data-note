package com.moon.springdata.jpa.dao;

import com.moon.springdata.jpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 文章评论 comment 表持久层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 16:26
 * @description
 */
public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
}
