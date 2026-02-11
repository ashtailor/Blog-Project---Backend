package com.project.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name ="Comments")
@Entity
public class Comment 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String comment;
	
	@ManyToOne
	@JoinColumn(name ="blog_id")
	@JsonBackReference
	private Blog blog;
	
	@ManyToOne
	@JoinColumn(name ="user_id")
	User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", blog=" + blog + ", user=" + user + "]";
	}

	public Comment(Integer id, String comment, Blog blog, User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.blog = blog;
		this.user = user;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
