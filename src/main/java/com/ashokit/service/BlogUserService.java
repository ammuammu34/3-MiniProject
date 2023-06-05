package com.ashokit.service;

import java.util.List;

import com.ashokit.binding.BlogUserForm;
import com.ashokit.binding.LoginForm;
import com.ashokit.entity.BlogPost;

public interface BlogUserService {
	public boolean  blogUser(BlogUserForm form) throws Exception;
	public String login(LoginForm loginform) ;
	boolean disableBlog(Integer postId);
	public List<BlogPost> viewBlogs(Integer userId);

}
