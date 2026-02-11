package com.project.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.demo.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>
{
	List<Blog> findAll();
	Optional<Blog> findById(Integer id);
}
