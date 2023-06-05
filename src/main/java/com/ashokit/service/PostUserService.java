package com.ashokit.service;

import java.util.List;

import com.ashokit.binding.BlogPostForm;
import com.ashokit.entity.BlogPost;

public interface PostUserService {
	public boolean  postuser(BlogPostForm form);

	public List<BlogPost> findAll(BlogPostForm form);


	public List<BlogPost> getSearchBlogs(String search);


}
