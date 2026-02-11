package com.project.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="likes",
			uniqueConstraints = 
			{
				@UniqueConstraint(columnNames = {"user_id","blog_id"})
			}
		)

public class Like 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer like_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne
	@JoinColumn(name ="blog_id")
	@JsonIgnore
	Blog blog;

	public Integer getLike_id() {
		return like_id;
	}

	public void setLike_id(Integer like_id) {
		this.like_id = like_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "Like [like_id=" + like_id + ", user=" + user + ", blog=" + blog + "]";
	}

	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	
	
	
	
}
