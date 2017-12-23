package Vishnu.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.BlogPost;
import Vishnu.Model.Blog;

@Repository
@Transactional
public class BlogPostImpl implements BlogPost {

	@Autowired
	private SessionFactory sessionFactory;
	public void saveblog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blog);
		
	}

}
