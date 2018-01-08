package Vishnu.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.BlogLikesDao;
import Vishnu.Model.Blog;
import Vishnu.Model.BlogLikes;
import Vishnu.Model.User;


@Repository
@Transactional
public class BlogLikesImpl implements BlogLikesDao{
	
	@Autowired
	private SessionFactory sessionF;

	public BlogLikes userlikes(Blog blog, User user) {
		Session session=sessionF.getCurrentSession();
		Query query=session.createQuery("from BlogLikes where blog.id=? and user.username=? ");
		System.out.println("Blog Id : "+ blog.getId());
		System.out.println("username : "+user.getUsername());
		query.setInteger(0, blog.getId());
		query.setString(1, user.getUsername());
		BlogLikes bloglikes=(BlogLikes)query.uniqueResult();
		System.out.println(bloglikes);
		return bloglikes;
	}

	public Blog updatelikes(Blog blog, User user) {
		Session session=sessionF.getCurrentSession();
		BlogLikes bloglikes=userlikes(blog,user);
		if(bloglikes==null){
			BlogLikes insertlikes=new BlogLikes();
			insertlikes.setBlog(blog);
			insertlikes.setUser(user);
			session.save(insertlikes);
			session.update(blog);
		}else{
			session.delete(bloglikes);
			blog.setLikes(blog.getLikes()-1);
			session.merge(blog);
		}
		return blog;
	}
	 
}
