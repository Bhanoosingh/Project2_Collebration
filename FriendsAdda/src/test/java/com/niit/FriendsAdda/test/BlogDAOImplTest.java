package com.niit.FriendsAdda.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.FriendsAdda.DAO.BlogDAO;
import com.niit.FriendsAdda.model.Blog;

public class BlogDAOImplTest {
	
	private static Blog blog;
	private static BlogDAO blogDao;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blog =new Blog();
		blogDao = (BlogDAO) context.getBean("blogDAO");
	} 
	@Ignore
	@Test
	public void addBlogTest() throws ParseException {
		SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
		blog.setBlogName("Code Cooking");
		blog.setUserName("bhanoosingh18");
		blog.setBlogContent("Code Cooking Content");
		blog.setCreateDate(textFormat.parse("14-04-2018"));
		blog.setStatus("NA");
		blog.setLikes(0);
		assertTrue("Problem in inserting blog table", blogDao.addBlog(blog));
	}
	@Ignore
	@Test
	public void updateBlogTest() {
		blog=blogDao.getBlog(1);
		blog.setStatus("A");
		assertTrue("Problem in updating status of blog", blogDao.updateBlog(blog));
	}
	@Ignore
	@Test
	public void deleteBlogTest() {
		blog=blogDao.getBlog(-46);
		assertTrue("Problem in deleting blog",blogDao.deleteBlog(blog));
	}
	
	@Test
	public void listBlogTest() {
		
		List<Blog> list = blogDao.listBlog("bhanoosingh18");
		assertTrue("Problem in listing blog",list.size()>0);
		for(Blog b:list) {
			
			System.out.print(b.getBlogId()+"::");
			System.out.print(b.getBlogName()+"::");
			System.out.print(b.getBlogContent()+"::");
			System.out.println(b.getUserName()+"::");
		}
	}

}