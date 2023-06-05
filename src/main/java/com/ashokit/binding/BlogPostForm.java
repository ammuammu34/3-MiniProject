package com.ashokit.binding;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogPostForm {
	
	private Integer postId;
	private String title;
	private String description;
	private String content;
	private LocalDate createdOn;
	private LocalDate updatedOn;

}
