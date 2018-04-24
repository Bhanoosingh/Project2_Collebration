package com.niit.FriendsAdda.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.FriendsAdda.DAO.ForumDAO;
import com.niit.FriendsAdda.model.Forum;

public class ForumDAOImplTest {

	static Forum forum;
	static ForumDAO forumDao;
	@SuppressWarnings("resource")
	@BeforeClass
	public static void init() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		forum =new Forum();
		forumDao = (ForumDAO) context.getBean("forumDAO");
	} 
	
	@Test
	public void addForumTest() throws ParseException {
		SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
		forum.setForumName("Forum-HiFiShopee");
		forum.setUserName("bhanoosingh18");
		forum.setForumContent("HiFiShopee forum content");
		forum.setCreatedDate(textFormat.parse("12-04-2018"));
		forum.setStatus("NA");
		
		assertTrue("Problem in adding forum into the table", forumDao.addForum(forum));
		
	}

	@Ignore
	@Test
	public void updateForumTest() {
		
		forum=forumDao.getForum(-45);
		forum.setForumName("Forum-Demo");
		forum.setUserName("bhanoosingh18");
		forum.setForumContent("This is forum content");
		forum.setStatus("A");
		assertTrue("Problem in updating status of forum",forumDao.updateForum(forum));
	}
	
	@Ignore
	@Test
	public void deleteForumTest() {
		
		forum=forumDao.getForum(-44);
		assertTrue("Problem in deleting forum",forumDao.deleteForum(forum));
	}
	
	
	@Test
	public void listForumTest() {
		List<Forum> forumList=forumDao.listForum("bhanoosingh18");
		assertTrue("Problem in listing Forum",forumList.size()>0);
		for(Forum f:forumList) {
			System.out.print(f.getForumId()+"::");
			System.out.print(f.getForumName()+"::");
			System.out.print(f.getForumContent()+"::");
			System.out.println(f.getUserName()+"::");
		}
	}
}