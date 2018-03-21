package com.niit.FriendsAdda.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.FriendsAdda.DAO.UserDAO;
import com.niit.FriendsAdda.model.UserDetail;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public boolean addUser(UserDetail user) {
		try{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteUser(UserDetail user) {
		try{
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateUser(UserDetail user) {
		try{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public UserDetail getUser(int userId) {
		try {
			Session session = sessionFactory.openSession();

			UserDetail user = session.get(UserDetail.class, userId);
			session.close();
			return user;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return null;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetail> listUser() {
		List<UserDetail> list = sessionFactory.getCurrentSession().createQuery("from User").list();	
		return list;
	}

}
