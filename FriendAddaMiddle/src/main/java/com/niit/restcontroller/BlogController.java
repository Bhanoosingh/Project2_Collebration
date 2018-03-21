package com.niit.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.FriendsAdda.DAO.BlogDAO;
import com.niit.FriendsAdda.model.Blog;

@RestController
public class BlogController {

	@Autowired
	BlogDAO blogDAO;

	@GetMapping(value = "/demo")
	public ResponseEntity<String> demoPurpose() {
		return new ResponseEntity<String>("Demo Data", HttpStatus.OK);
	}

	@GetMapping(value = "/listBlogs")
	public ResponseEntity<List<Blog>> getlistBlogs() {

		List<Blog> listBlog = blogDAO.listBlog("Bhanoo");
		if (listBlog.size() > 0) {
			return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.OK);
		} else

		{
			return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog){
		blog.setCreateDate(new java.util.Date());
		blog.setUsername("Bhanoo");
		blog.setStatus("A");
		
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}

}
