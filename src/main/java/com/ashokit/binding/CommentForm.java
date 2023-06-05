package com.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;


@Data
public class CommentForm {
	private Integer postId;
	private String name;
	private String email;
	private String comment;
	private LocalDate createdDate;
	
}
