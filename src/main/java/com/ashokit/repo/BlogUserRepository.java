package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.BlogUser;

public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {

	public  BlogUser findByEmail(String email);
	 public BlogUser findByEmailAndPassword(String email,String password);
	

}
