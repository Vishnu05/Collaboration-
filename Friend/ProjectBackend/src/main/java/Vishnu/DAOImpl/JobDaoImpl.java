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
	private SessionFactory sessionFactory;
	public void saveJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
	}

	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		return query.list();
	}

	public Job getJobById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class,id);
		return job;
	}

	public Job deletejob(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=new Job();
		job.setId(id);
		session.delete(job);
		return job;
	}

	 

}
