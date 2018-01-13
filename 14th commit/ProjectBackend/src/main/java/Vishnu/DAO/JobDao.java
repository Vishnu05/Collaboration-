package Vishnu.DAO;
 


import java.util.List;

import Vishnu.Model.Job;

public interface JobDao {
	void saveJob(Job job);
	List<Job> getAllJobs();
	Job getJobById(int id);
	Job deletejob(int id);
	void applyjob(int id);
	
}
