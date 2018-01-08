package Vishnu.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Vishnu.DAO.BlogLikesDao;
import Vishnu.DAO.BlogPost;
import Vishnu.DAO.UserDAO;
import Vishnu.Model.Blog;
import Vishnu.Model.BlogComment;
import Vishnu.Model.BlogLikes;
import Vishnu.Model.ErrorClazz;
import Vishnu.Model.User;

@RestController
public class BlogController {

	@Autowired
	private BlogPost blogpost;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private BlogLikesDao bloglikesdao;

	@RequestMapping(value = "/saveblog", method = RequestMethod.POST)
	public ResponseEntity<?> savejob(@RequestBody Blog blog, HttpSession session) {

		if (session.getAttribute("username") == null) {
			ErrorClazz error = new ErrorClazz(5, "UnAuthorized User");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}

		String username = (String) session.getAttribute("username");
		// username="sriram";
		User user = userDAO.getUserByUsername(username);
		blog.setPostedBy(user);
		blog.setPostedOn(new Date());
		try {
			blogpost.saveblog(blog);

		} catch (Exception e) {
			ErrorClazz error = new ErrorClazz(6, "Unable to insert blog post details " + e.getMessage());
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "/getblogs/{approved}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogs(@PathVariable int approved, HttpSession session) {
		String username = (String) session.getAttribute("username");
		// username="vishnu";
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		if (approved == 0) {
			User user = userDAO.getUserByUsername(username);
			if (!user.getRole().equals("ADMIN")) {
				ErrorClazz error = new ErrorClazz(7, "Access Denied");
				return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
			}
		}
		List<Blog> blog = blogpost.getBlogs(approved);
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);

	}

	@RequestMapping(value = "/getblog/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		// username="vishnu";
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		Blog blog = blogpost.getbyBlogId(id);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateapprovalstatus", method = RequestMethod.PUT)
	public ResponseEntity<?> updateApprovalStatus(@RequestBody Blog blog,
			@RequestParam(required = false) String rejectionReason, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		try {

			System.out.println(blog);
			blogpost.updateBlogPost(blog, rejectionReason);
		} catch (Exception e) {
			ErrorClazz error = new ErrorClazz(7, "Unable to update blogpost approval status " + e.getMessage());
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/userlikes/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> userlikes(@PathVariable int id,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDAO.getUserByUsername(username);
		Blog blog=blogpost.getbyBlogId(id);
		BlogLikes bloglikes=bloglikesdao.userlikes(blog, user);
		return new ResponseEntity<BlogLikes>(bloglikes,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updatelikes",method=RequestMethod.PUT)
	public ResponseEntity<?> updatelikes(@RequestBody Blog blog,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDAO.getUserByUsername(username);
		Blog updatedblog=bloglikesdao.updatelikes(blog, user);
		return new ResponseEntity<Blog>(updatedblog,HttpStatus.OK);
		
	}  
	
	@RequestMapping(value="/addcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addcomment(@RequestParam String commentedText,@RequestParam int id,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User commentedBy=userDAO.getUserByUsername(username);
		BlogComment blogcomment=new BlogComment();
		blogcomment.setCommentedText(commentedText);
		blogcomment.setCommentedBy(commentedBy);	
		Blog blog= blogpost.getbyBlogId(id);
		blogcomment.setBlog(blog);
		blogcomment.setCommentedOn(new Date());
		
		try{
			blogpost.addcomment(blogcomment);
			System.out.println(blogcomment);
		}
		catch(Exception f){
			System.out.println(f);
			System.out.println(f.getMessage());
			ErrorClazz error=new ErrorClazz(7,"Unable to review the blog");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		blog=blogpost.getbyBlogId(id);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	
}
