package com.niit.FriendsAdda.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.FriendsAdda.DAO.ForumDAO;
import com.niit.FriendsAdda.model.Forum;

public class ForumDAOImplTest {

	private static AnnotationConfigApplicationContext context;
	private static ForumDAO forumDAO=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		forumDAO=(ForumDAO)context.getBean("forumDAO");
	}

	@Test
	public void addForumTest() {
		Forum nforum=new Forum();
		
		nforum.setForumName("New Forum");
		nforum.setCreatedate(new Date());
		nforum.setForumContent("Forum Content");
		nforum.setStatus("A");
		nforum.setUsername("Bhanoo");
		
		
		assertTrue("Got some error in creating Forum", forumDAO.addForum(nforum));
	}

}
