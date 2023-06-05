package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.binding.BlogPostForm;
import com.ashokit.binding.BlogUserForm;
import com.ashokit.binding.LoginForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.repo.BlogPostRepository;
import com.ashokit.repo.CommentRepository;
import com.ashokit.service.BlogUserService;
import com.ashokit.service.PostUserService;


@Controller
public class BlogUserController {
	@Autowired
	private BlogUserService service;
	@Autowired
	private CommentRepository commentrepo;
	@Autowired
	private PostUserService postservice;
	@Autowired
	private BlogPostRepository repo;

	@GetMapping("/")
	public String showIndexForm(@ModelAttribute("post")  BlogPostForm form ,Model model) {
		List<BlogPost> users = postservice.findAll(form);
		model.addAttribute("post", users);
		return "index";
	}


	@GetMapping("/loadsignup")
	public String showSignupForm(Model model) {
		model.addAttribute("signup", new BlogUserForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String showBlogUserForm(@Validated @ModelAttribute("signup")  BlogUserForm form ,BindingResult result,Model model) throws Exception {
		//model.addAttribute("signup", new BlogUserForm());
		if (result.hasErrors()) {
            return "signup";
        }
		boolean users = service.blogUser(form);
		if (users) {
			model.addAttribute("smsg", "user saved....");
		} else {
			model.addAttribute("emsg", "email already exist");
		}
		return "signup";
	}

	@GetMapping("/loadlogin")
	public String loadLogin(Model model) {
		model.addAttribute("login", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String loadLogin(@Validated @ModelAttribute("login") LoginForm form,BindingResult result, Model model) throws Exception {

		if (result.hasErrors()) {
            return "login";
        }
		String status = service.login(form);
		if (status.contains("Success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errmsg", status);
		return "login";
	}
	@GetMapping("/dashboard")
	public String loadDashboard(Model model) {
		System.out.println("dashboard method called...");
		model.addAttribute("post", repo.findAll());
		return "dashboard";
	}
	@GetMapping("/searchblogs")

	public String search(@RequestParam("title") String title, Model model) {
		List<BlogPost> searchResults = repo.findByTitleContainingIgnoreCase(title);
		model.addAttribute("post", searchResults);

		return "index";

	}
	
	@GetMapping("/search")
	public String searchBlogs(@RequestParam("search")String search ,Model model) {

		System.out.println(search);
		List<BlogPost> blogs = postservice.getSearchBlogs(search);
		model.addAttribute("post", blogs);
		

		return "dashboard";
	}
	@GetMapping("/deletecomment")
	public String deleteComment(@RequestParam("commentId") Integer commentId,Model model) {

		//commentrepo.deleteById(commentId);
		
		  commentrepo.deleteById(commentId);
			commentrepo.findById(commentId);
			model.addAttribute("msg", "product deleted");
			model.addAttribute("post",commentrepo.findAll());

		return "redirect:/comment";

	}
	

}
