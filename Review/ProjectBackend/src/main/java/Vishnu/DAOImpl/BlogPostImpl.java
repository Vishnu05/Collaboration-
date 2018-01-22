package Vishnu.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.BlogPost;
import Vishnu.Model.Blog;
import Vishnu.Model.BlogComment;
import Vishnu.Model.Notification;

@Repository
@Transactional
public class BlogPostImpl implements BlogPost {

	@Autowired
	private SessionFactory sessionF;

	public void saveblog(Blog blog) {
		Session session = sessionF.getCurrentSession();
		session.save(blog);

	}

	public List<Blog> getBlogs(int approved) {
		Session session = sessionF.getCurrentSession();
		Query query = session.createQuery("from Blog where approved=" + approved);
		return query.list();
	}

	public Blog getbyBlogId(int id) {
		Session session = sessionF.getCurrentSession();
		Blog blog = (Blog) session.get(Blog.class, id);
		return blog;
	}

	public void updateBlogPost(Blog blog, String rejectionReason) {
		Session session = sessionF.getCurrentSession();
		Notification notification = new Notification();
		notification.setBlogtitle(blog.getBlogtitle());
		notification.setUsername(blog.getPostedBy().getUsername());
		if (blog.isApproved()) {

			session.update(blog);
			notification.setApprovalstatus("Approved");
			session.save(notification);
		} else {
			System.out.println(rejectionReason);
			if (rejectionReason.equals(""))
				notification.setRejectionreason("Not Mentioned by Admin");
			else
				notification.setRejectionreason(rejectionReason);
			notification.setApprovalstatus("Rejected");
			session.save(notification);
			session.delete(blog);

		}
	}

	public void addcomment(BlogComment blogcomment) {
		Session session = sessionF.getCurrentSession();
		session.save(blogcomment);
	}

	

}
