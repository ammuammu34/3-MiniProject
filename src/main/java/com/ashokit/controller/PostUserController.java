package com.ashokit.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.binding.BlogPostForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.repo.BlogPostRepository;
import com.ashokit.service.BlogUserService;
import com.ashokit.service.PostUserService;

@Controller
public class PostUserController {
	
	@Autowired
	private PostUserService service;
	@Autowired
	private BlogUserService blogservice;
	
	@Autowired
	private BlogPostRepository repo;
	
	@Autowired
	private HttpSession session;

	@GetMapping("/loadpost")
	public String showSignupForm(Model model) {
		model.addAttribute("post", new BlogPostForm());
		return "post";
	}
	
	@PostMapping("/post")
	public String showBlogUserForm(@ModelAttribute("post")  BlogPostForm form ,Model model) {
		boolean users = service.postuser(form);
		if (users) {
			model.addAttribute("smsg", "Post saved....");
		} else {
			model.addAttribute("emsg", "error occured");
		}
		return "post";
	}

	/*
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer postId,Model model ) {
		repo.deleteById(postId);
		commentrepo.findById(postId);
		model.addAttribute("msg", "product deleted");
		model.addAttribute("post",repo.findAll());
		return "dashboard";
	}*/

	@GetMapping("/delete")
	public String deleteBlog(@RequestParam("id") Integer postId,Model model) {

		boolean status = blogservice.disableBlog(postId);
		
		if(status) {
			model.addAttribute("success", "post deleted successfully");
		}else {
			model.addAttribute("error","Deletion failed");
		}

		Integer userId = (Integer)session.getAttribute("userId");

		List<BlogPost> blogs =blogservice.viewBlogs(userId);
		

		model.addAttribute("post",blogs);

		return "dashboard";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Integer postId,Model model) {
		Optional<BlogPost> findById = repo.findById(postId);
		if (findById.isPresent()) {
			BlogPost studentEnqEntity = findById.get();
			model.addAttribute("post", studentEnqEntity);
		}
		return "post";
	}

}
