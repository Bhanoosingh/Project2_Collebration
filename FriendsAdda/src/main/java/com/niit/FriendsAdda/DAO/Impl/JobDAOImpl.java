package com.niit.FriendsAdda.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.FriendsAdda.DAO.JobDAO;
import com.niit.FriendsAdda.model.ApplyJob;
import com.niit.FriendsAdda.model.Job;

@Repository
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean addJob(Job job) {
		
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteJob(Job job) {
		
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateJob(Job job) {
		
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplyJob> getAllAppliedJobDetails() {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<ApplyJob> appliedjobList = new ArrayList<ApplyJob>();
			Query query = session.createQuery("FROM ApplyJob");
			appliedjobList = query.list();
			return appliedjobList;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> listAllJobs() {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<Job> jobList = new ArrayList<Job>();
			Query query = session.createQuery("FROM Job");
			jobList = query.list();
			return jobList;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	@Override
	public boolean applyJob(ApplyJob job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Job getJob(int jobId) {
		try {
			Session session = sessionFactory.openSession();
			Job job = session.get(Job.class, jobId);
			return job;
		} catch (Exception e) {
			return null;
		}
	}

}