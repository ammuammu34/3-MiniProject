package com.ashokit.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.binding.BlogUserForm;
import com.ashokit.binding.LoginForm;
import com.ashokit.entity.BlogPost;
import com.ashokit.entity.BlogUser;
import com.ashokit.repo.BlogPostRepository;
import com.ashokit.repo.BlogUserRepository;
import com.ashokit.service.BlogUserService;
import com.ashokit.utils.PasswordEncrypter;
@Service
public class BlogUserServiceImpl implements BlogUserService {
	@Autowired
	private BlogUserRepository repo;
	@Autowired
	private BlogPostRepository postrepo;
	@Autowired
	private HttpSession session;
	
	
	@Override
	public boolean blogUser(BlogUserForm form) throws Exception {
	BlogUser users = repo.findByEmail(form.getEmail());
		if(users!= null ){
			return false;
		}
	     BlogUser user = new BlogUser();
		BeanUtils.copyProperties(form, user);
		String encryptPassword = PasswordEncrypter.encrypt(form.getPassword());
		user.setPassword(encryptPassword);
		repo.save(user);
		return true;
		}
	@Override
	public String login(LoginForm loginform)  {
		String encryptPassword = PasswordEncrypter.encrypt(loginform.getPassword());
		BlogUser entity = repo.findByEmailAndPassword(loginform.getEmail(),encryptPassword);
		if(entity ==null) {
			return "invalid credentials";
		}
		session.setAttribute("userId",entity.getUserId());
		return "Success";
	}
	@Override
	public boolean disableBlog(Integer postId) {
		BlogPost blog = null;

		Optional<BlogPost> findById = postrepo.findById(postId);

		if(findById.isPresent()) {
			blog = findById.get();
			blog.setStatus("InActive");
		}

		postrepo.save(blog);

		return true;
	

	}
	@Override
	public List<BlogPost> viewBlogs(Integer userId) {
		BlogUser user = null; 

		Optional<BlogUser> findById = repo.findById(userId);

		if(findById.isPresent()) {
			user = findById.get();
		}


		BlogPost blog = new BlogPost();
		blog.setUser(user);
		blog.setStatus("Active");

		Example<BlogPost> example = Example.of(blog);

		List<BlogPost> blogs = postrepo.findAll(example);

		return blogs;
	
	}
}

