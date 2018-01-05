package Vishnu.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Vishnu.DAO.BlogLikesDao;
import Vishnu.Model.Blog;

public class BlogLikesImpl implements BlogLikesDao{

	private SessionFactory sessionFactory;
	public void saveLikes(Blog blog) {

		Session session=sessionFactory.getCurrentSession();
		//session.save(likes)
		
	}

}
