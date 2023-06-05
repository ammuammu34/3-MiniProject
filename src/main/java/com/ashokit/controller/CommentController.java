package com.ashokit.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.binding.CommentForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.entity.Comment;
import com.ashokit.repo.BlogPostRepository;
import com.ashokit.repo.CommentRepository;
import com.ashokit.service.CommentUserService;

@Controller
public class CommentController {
	@Autowired
	private CommentRepository commentrepo;
	@Autowired
	private BlogPostRepository repo;
	@Autowired
	private CommentUserService commentservice;
	@Autowired
	private HttpSession session;

@PostMapping("/comment")
	public String saveComment(@ModelAttribute("comment") CommentForm form,Model model) {
	Integer postId = (Integer)session.getAttribute("postId");

	commentservice.addComment(form,postId);
	

	Optional<BlogPost> findById = repo.findById(postId);
	if(findById.isPresent()) {
		model.addAttribute("post",findById.get());
		model.addAttribute("comment",new Comment());
		model.addAttribute("display",findById.get().getComment());
	}
		return "commentspage";
	}

	@GetMapping("/commentspage")
	public String loadReadPage(@RequestParam("id") Integer postId,@ModelAttribute("comment") CommentForm form,Model model) {
		System.out.println("read method called...");
		
		session.setAttribute("postId",postId);

		Optional<BlogPost> findById = repo.findById(postId);
		if(findById.isPresent()) {
			model.addAttribute("post",findById.get());
			model.addAttribute("comment",new Comment());
			model.addAttribute("display",findById.get().getComment());
		}
		
		return "commentspage";
	}

	@GetMapping("/comment")
	public String getComment(@ModelAttribute("comment") CommentForm form,Model model) {
		System.out.println("comment method retrieved...");
		model.addAttribute("comment",commentrepo.findAll());
		return "comment";
	}



}
