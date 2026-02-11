package com.project.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LikeRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer blogId;

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public LikeRequest(Integer blogId) {
		super();
		this.blogId = blogId;
	}

	public LikeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LikeRequest [blogId=" + blogId + "]";
	}
	

}
