package com.niit.FriendsAdda.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.FriendsAdda.DAO.BlogDAO;
import com.niit.FriendsAdda.model.Blog;

public class BlogDAOImplTest {
	private static AnnotationConfigApplicationContext context;
	private static BlogDAO blogDAO=null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}

	@Test
	public void testAddBlog() {
		Blog nblog=new Blog();
		Date ndate=new Date();
		
		nblog.setBlogName("HappyHours");
		nblog.setBlogContent("And Hobbies");
		nblog.setCreateDate(ndate);
		nblog.setStatus("A");
		nblog.setUsername("Bhanoo Pratap Singh");
		assertTrue("Had some issue during adding a blog", blogDAO.addBlog(nblog));
	}

	@Ignore
	@Test
	public void testDeleteBlog() {
		Blog nblog=blogDAO.getBlog(1);
		
		assertTrue("Had some issue during deleting a blog",blogDAO.deleteBlog(nblog));
	}

	@Ignore
	@Test
	public void testUpdateBlog() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetBlog() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testApproveBlog() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testRejectBlog() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testListBlog() {
		fail("Not yet implemented");
	}

}
