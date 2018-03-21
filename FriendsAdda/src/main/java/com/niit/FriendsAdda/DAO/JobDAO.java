package com.niit.FriendsAdda.DAO;

import com.niit.FriendsAdda.model.Job;

public interface JobDAO {
public boolean addJob(Job job);
public boolean deleteJob(Job job);
public boolean updateJob(Job job);
public Job getJob(int jobId);
}