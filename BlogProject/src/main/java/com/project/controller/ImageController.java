package com.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController 
{
	private final String upload_dir ="C:/blog_images";
	@PostMapping("/upload/image")
	public String uploadImage(@RequestParam("file") MultipartFile file ) throws IOException
	{	
		if(file.isEmpty())
		{
			return "File is Empty";
		}
		 String originalFilename = file.getOriginalFilename();
		 Path path = Paths.get(upload_dir,originalFilename );//path.get convert string path into path object so java can read, delete,create,copy file.
		 Files.write(path, file.getBytes());// write save the uploaded file,create a file write data inside it
		 String url ="http://localhost:8080/images"+ originalFilename;
		 return url;
	}
}
