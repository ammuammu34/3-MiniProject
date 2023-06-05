package com.ashokit.serviceimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.CommentForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.entity.BlogUser;
import com.ashokit.entity.Comment;
import com.ashokit.repo.BlogPostRepository;
import com.ashokit.repo.BlogUserRepository;
import com.ashokit.repo.CommentRepository;
import com.ashokit.service.CommentUserService;

@Service
public class CommentServiceImpl implements CommentUserService {

	@Autowired
	private CommentRepository repo;
	@Autowired
	private BlogPostRepository blogrepo;
	@Autowired
	private BlogUserRepository userrepo;
	
	@Override
	public String addComment(CommentForm form,Integer postId) {
		 Optional<BlogPost> blogPost = blogrepo.findById(postId);
		BlogPost blogPost2 = blogPost.get();
		Comment user = new Comment();
		
		BeanUtils.copyProperties(form, user);
		
		user.setPostuser(blogPost2);
		
		repo.save(user);
		
		return "save successfully";
	}
	@Override
	public BlogPost getStatus(Integer postId) {
		Optional<BlogPost> findById	=blogrepo.findById(postId);
		BlogPost user = findById.get();
		return user;

	}
	@Override
	public List<Comment> getComments(Integer postId) {
		BlogUser user = userrepo.findById(postId).get();

		List<Comment> names = new ArrayList<>();
		List<Comment> findAll = repo.findAll();

		for(Comment entity: findAll){
			names.add(entity);
		}

		return findAll;	
	}

}




