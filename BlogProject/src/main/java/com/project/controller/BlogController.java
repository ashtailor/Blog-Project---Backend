package com.project.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.demo.Blog;
import com.project.demo.Comment;
import com.project.demo.Like;
import com.project.demo.User;
import com.project.repository.BlogRepository;
import com.project.repository.CommentRepository;
import com.project.repository.LikeRepository;
import com.project.repository.UserRepository;
import com.project.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BlogController 
{
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LikeRepository likeRepository;
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	CommentRepository commentRepository; 
	@GetMapping("/api/blog")
	public List<Blog> getBlog()
	{
		List<Blog> blog = blogRepository.findAll();
		return blog;
	}
	
	@GetMapping("/api/blog/{id}")
	public Blog getBlogId(@PathVariable Integer id)
	{
		return blogRepository.findById(id).get();
		
	}
	
	@PostMapping("/api/blog/save")
	public Blog saveBlog(@RequestBody Blog blog)
	{
		Blog saved = blogRepository.save(blog);
		return saved;
	}
	
	@PostMapping("/api/blog/like")
	public ResponseEntity<?> saveLike(@RequestBody Blog blogFromUi)
	{	
		//1. find blog by id. 
		Integer blog_id = blogFromUi.getBlogId();
		Optional<Blog> blogOpt = blogRepository.findById(blog_id);
		if(!blogOpt.isPresent())
		{
			return ResponseEntity.badRequest().body("blog not found");
		}
		
		// 2. read Authorization header from frontend
		String authHeader = request.getHeader("Authorization");//backned read token that frontend sends. 
		System.out.println("Recieved token..."+ authHeader);//header is extra information sent with an API. read its value.
		if(authHeader== null ||!authHeader.startsWith("Bearer"))
		{
			return ResponseEntity.status(404).body("Invalid or missing token");
		}
	
		//3. Extract JWT Token
		String token = authHeader.substring(7); //removes bearer and keep the jwt token only
		System.out.println("token" + token);
		
		//4 Extract email username from token
		String email = JwtUtil.extractUsername(token);
		System.out.println("Email"+ email);
		if(email == null)
		{
			return ResponseEntity.status(401).body("Invalid token");
		}
		//jwt contains user information inside token backned stored email(backened knows which user is logged in)
		
		//5 Find user by email
		User user = userRepository.findByEmail(email);
		System.out.println("User");
		if(user == null)
		{
			return ResponseEntity.status(404).body("User not found");
		}
		
		Blog blog = blogOpt.get();
		
		// CHECK DUPLICATE LIKE (ADD HERE)
		if(likeRepository.existsByUserAndBlog(user, blog)) 
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("You already liked this blog");
		}
		
		//6 Create Like object
		Like like = new Like();
		like.setUser(user);
		like.setBlog(blog);
		
		//7 Save Like
		likeRepository.save(like);
		return ResponseEntity.ok("liked saved successfully!");
	}
	
	@GetMapping("/api/comment/{blog_id}")//5
	public List<Comment> getComment(@PathVariable Integer blog_id)//5
	{
		return commentRepository.findByBlog_BlogId(blog_id);
	}
	
	@PutMapping("/api/blog/{id}")
	public Blog editBlog(@PathVariable Integer id , @RequestBody Blog updatedBlog )
	{
		Optional<Blog> blogId = blogRepository.findById(id);
		Blog blog = blogId.get();
		blog.setTitle(updatedBlog.getTitle());
		blog.setSubtitle(updatedBlog.getSubtitle());
		blog.setDescription(updatedBlog.getDescription());
		System.out.println("Updated Blog "+  blog.getBlogId());
		return blogRepository.save(blog);
	}
	
	@DeleteMapping("api/blog/{id}")
	public ResponseEntity<String> deleteBlog(@PathVariable Integer id)
	{
		if(!blogRepository.existsById(id))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			 .body("blog not found");
		}
		likeRepository.deleteByBlog_BlogId(id);
		blogRepository.deleteById(id);
		return ResponseEntity.ok("Blog Deleted Succesfully");
	}
	
}
