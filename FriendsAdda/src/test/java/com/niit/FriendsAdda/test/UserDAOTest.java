/**
 * 
 */
package com.niit.FriendsAdda.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.FriendsAdda.DAO.UserDAO;
import com.niit.FriendsAdda.model.UserDetail;

/**
 * @author Bhanoo Pratap Singh
 *
 */
public class UserDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO=null;
	private static UserDetail user=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
	}

	@Ignore
	@Test
	public void testAddUser() {
		user=new UserDetail();
		user.setEmail("abc@gmail.com");
		user.setAddress("637/B-Block Indra Nagar Lucknow");
		user.setName("Abc Singh");
		user.setPassword("123456");
		user.setPhone("8960992608");
		user.setRole("user");
		user.setIsOnline("offline");
		
		assertTrue("Had some problem during saving user",userDAO.addUser(user));
	}
	
	@Ignore
	@Test
	public void testUpdateUser() {
		user=userDAO.getUser("bhanoosingh1@gmail.com");
		
		user.setAddress("637/B-Block Indra Nagar Lucknow");
		user.setName("Bhanoo P Singh");
		user.setPassword("123456");
		user.setPhone("8960992608");
		user.setRole("user");
		user.setIsOnline("offline");
		
		assertTrue("Had some problem during Updating user",userDAO.updateUser(user));
	}
	@Ignore
	@Test
	public void testDeleteUser() {
		user=userDAO.getUser("abc@gmail.com");
		assertTrue("Had some problem during Deleting user",userDAO.deleteUser(user));
	}
	@Test
	public void testGetUser() {
		assertNotNull("Had some problem during Fetching user",userDAO.getUser("bhanoosingh1@gmail.com"));
	}
	@Test
	public void testCheckLogin() {
		
	}
}
