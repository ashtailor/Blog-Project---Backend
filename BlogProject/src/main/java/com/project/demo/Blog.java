package com.project.demo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Blog 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer blogId;
	String title;
	String subtitle;
	String description;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "blog")
	private List<Like> likes;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Comment> comment;

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}



	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Blog(Integer blogId, String title, String subtitle, String description, User user, List<Like> likes,
			List<Comment> comment) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.user = user;
		this.likes = likes;
		this.comment = comment;
	}

	
}
