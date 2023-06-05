package com.ashokit.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.BlogPostForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.repo.BlogPostRepository;
import com.ashokit.service.PostUserService;

@Service
public class PostUserServiceImpl  implements PostUserService{
	@Autowired
	private BlogPostRepository repo;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean postuser(BlogPostForm form) {
		Integer userId = (Integer)session.getAttribute("userId");
		BlogPost user = new BlogPost();
		BeanUtils.copyProperties(form, user);
		user.setStatus("active");
		repo.save(user);
		return true;

	}
	@Override
	public List<BlogPost> findAll(BlogPostForm form) {
		List<BlogPost> findAll = repo.findAll();
		return findAll;
	}



	@Override
	public List<BlogPost> getSearchBlogs(String search) {
		List<BlogPost> blogs = repo.findByTitleContainingIgnoreCase(search);
		return blogs;

	}
	
}
