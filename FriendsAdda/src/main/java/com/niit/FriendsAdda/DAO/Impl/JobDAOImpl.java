package com.niit.FriendsAdda.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.FriendsAdda.DAO.JobDAO;
import com.niit.FriendsAdda.model.Job;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public boolean addJob(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e){
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public Job getJob(int jobId) {
		try {
			Session session = sessionFactory.openSession();

			Job job = session.get(Job.class, jobId);
			session.close();
			return job;
		} catch (Exception e) {
			System.out.println("-----------------Exception--------------\n\n"+e);
			return null;
		}
	}

}
