package com.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.demo.User;
import com.project.repository.UserRepository;
import com.project.util.JwtUtil;

import io.jsonwebtoken.Jwt;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController 
{
	@Autowired
	UserRepository userRepository;
	@PostMapping("/api/register")
	public String register(@RequestBody User user)
	{
		userRepository.save(user);
		return "User Registerd Successfully!.......";
	}
	
	@PostMapping("/api/login")
	public Map<String, String> login(@RequestBody User userDetails)
	{
		User userFound = userRepository.findByEmail(userDetails.getEmail());
		if(userFound!= null && userFound.getPassword().equals(userDetails.getPassword()))
		{
			String token = null;
			try
			{
				token = JwtUtil.generateToken(userFound.getEmail());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return Map.of(
						"status","success",
						"message","login succesfully",
						"token",token
					);
		}
		return Map.of(
				"status","fail",
				"message","invalid credientials"
				);
	}

	
}
