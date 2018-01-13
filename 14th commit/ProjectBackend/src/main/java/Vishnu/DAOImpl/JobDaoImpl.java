package Vishnu.DAOImpl;
 
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.JobDao;
import Vishnu.Model.Job;

 
@Repository
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionF;
	public void saveJob(Job job) {
		Session session=sessionF.getCurrentSession();
		session.save(job);
	}

	public List<Job> getAllJobs() {
		Session session=sessionF.getCurrentSession();
		Query query=session.createQuery("from Job");
		return query.list();
	}

	public Job getJobById(int id) {
		Session session=sessionF.getCurrentSession();
		Job job=(Job)session.get(Job.class,id);
		return job;
	}

	public Job deletejob(int id) {
		Session session=sessionF.getCurrentSession();
		Job job=new Job();
		job.setId(id);
		session.delete(job);
		return job;
	}

	public void applyjob(int id) {
		
		Session session=sessionF.getCurrentSession();
		Job job=new Job();
		
		// TODO Auto-generated method stub
		
	} 

	 

}
