package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>
{
	 List<Comment> findByBlog_BlogId(Integer blog_id);
	
}
