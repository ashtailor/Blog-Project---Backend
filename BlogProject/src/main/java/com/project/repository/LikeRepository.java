package com.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.demo.Blog;
import com.project.demo.Like;
import com.project.demo.User;

public interface LikeRepository extends JpaRepository<Like, Integer>
{
	boolean existsByUserAndBlog(User user, Blog blog);
	void deleteByBlog_BlogId(Integer blogId);
}
