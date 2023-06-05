package com.ashokit.service;

import java.util.List;

import com.ashokit.binding.CommentForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.entity.Comment;

public interface CommentUserService {
	
	public String addComment(CommentForm form,Integer postId);
	public BlogPost getStatus(Integer postId);
	public List<Comment> getComments(Integer postId);

}
