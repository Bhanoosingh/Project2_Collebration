package com.niit.FriendsAdda.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.FriendsAdda.DAO.ForumDAO;
import com.niit.FriendsAdda.model.Forum;

@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public Forum getForum(int forumId) {
		try {
			Session session = sessionFactory.openSession();

			Forum forum = session.get(Forum.class, forumId);
			session.close();
			return forum;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return null;
		}
	}

	@Transactional
	@Override
	public boolean approveForum(Forum forum) {
		try {
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean rejectForum(Forum forum) {
		try {
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> listForum(String username) {
		Session session = sessionFactory.openSession();

		List<Forum> forumList = session.createQuery("from Forum where username = :username").setParameter("name", username).list();// user1 is a preexisting user
		
		return forumList;
	}

}