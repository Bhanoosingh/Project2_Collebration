package com.niit.FriendsAdda.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.FriendsAdda.DAO.JobDAO;
import com.niit.FriendsAdda.model.Job;

public class JobDAOTest {

	private static Job job;
	private static JobDAO jobDao;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void init() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		job =new Job();
		jobDao = (JobDAO) context.getBean("jobDAO");
	} 
	@Ignore
	@Test
	public void addJobTest() throws ParseException {
		SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
		job.setJobDesignation("TechMentor");
		job.setJobDesc("Should have frameworks(Spring,Hibernate,Angular JS) knowledge and on database (Oracle,H2)");
		job.setSalary(150000);
		job.setLastDateApply(textFormat.parse("12-06-2017"));
		job.setCompany("NIIT");
		job.setLocation("Lucknow");
		
		assertTrue("Problem in adding job into the table",jobDao.addJob(job));
		
	}
	@Ignore
	@Test
	public void updateJobTest()throws ParseException {
		
		SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
		job=jobDao.getJob(-46);
		job.setJobDesignation("Devops Developer");
		job.setJobDesc("Should have frameworks(Spring,Hibernate,Angular JS) knowledge and on database (Oracle,H2) and on core java");
		job.setSalary(150000);
		job.setCompany("Java Developer");
		job.setLastDateApply(textFormat.parse("12-06-2017"));
		job.setLocation("Lucknow Saprumarg");
		assertTrue("Problem in updating Job",jobDao.updateJob(job));
		
	}
	@Ignore
	@Test
	public void deleteJobTest() {
		
		job=jobDao.getJob(-46);
		assertTrue("Problem in deleting job", jobDao.deleteJob(job));
		
	}
	@Test
	public void listForumTest() {
		List<Job> jobList=jobDao.listAllJobs();
		assertTrue("Problem in listing Job",jobList.size()>0);
		for(Job j:jobList) {
			System.out.print(j.getJobId()+"::");
			System.out.print(j.getJobDesignation()+"::");
			System.out.print(j.getJobDesc()+"::");
			System.out.print(j.getLocation()+"::");
			System.out.print(j.getSalary()+"::");
			System.out.println(j.getLastDateApply()+"::");
		}
	}
}
