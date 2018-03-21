package com.niit.FriendsAdda.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.FriendsAdda.DAO.BlogDAO;
import com.niit.FriendsAdda.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	
	
	@Transactional
	@Override
	public boolean addBlog(Blog blog) {
		try{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean deleteBlog(Blog blog) {
		try{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean updateBlog(Blog blog) {
		try{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Override
	public Blog getBlog(int blogId) {
		try{
			Session session=sessionFactory.openSession();
			Blog blog=session.get(Blog.class,blogId);
			return blog;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return null;
		}
	}

	@Transactional
	@Override
	public boolean approveBlog(Blog blog) {
		try{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
			}
			catch(Exception e){
				System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
			}
	}

	@Transactional
	@Override
	public boolean rejectBlog(Blog blog) {
		try{
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
			}
			catch(Exception e){
				System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> listBlog(String username) {
		List<Blog> list = sessionFactory.getCurrentSession().createQuery("from Blog where username:username").setParameter("username", username).list();	
		return list;
	}

}
