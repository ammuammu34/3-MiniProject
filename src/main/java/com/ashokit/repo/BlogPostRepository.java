package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{

	@Query(value = "Select post from BlogPost post where post.title LIKE %?1%")
	public 	List<BlogPost> findByTitleContainingIgnoreCase(String title);
}
