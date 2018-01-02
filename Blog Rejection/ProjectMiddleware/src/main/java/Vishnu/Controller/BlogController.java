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
import org.springframework.web.bind.annotation.RestController;

import Vishnu.DAO.BlogPost;
import Vishnu.DAO.UserDAO;
import Vishnu.Model.Blog;
import Vishnu.Model.ErrorClazz;
import Vishnu.Model.User;

@RestController
public class BlogController {

	@Autowired
	private BlogPost blogpost;
	@Autowired
	private UserDAO userDAO;

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

	@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogs(@PathVariable int approved,HttpSession session){
		String username=(String)session.getAttribute("username");
		//username="vishnu";
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		if(approved==0){
			User user=userDAO.getUserByUsername(username);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz error=new ErrorClazz(7,"Access Denied");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		}
		List<Blog> blog=blogpost.getBlogs(approved);
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);

	}
	
	@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session){
		String username=(String)session.getAttribute("username");
	//	username="vishnu";
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401
		}
		Blog blog=blogpost.getbyBlogId(id);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	  
	@RequestMapping(value="/updateblogpost",method=RequestMethod.POST)
	public ResponseEntity<?> updateblogpost(@RequestBody Blog blog,HttpSession session){
	String username=(String)session.getAttribute("username");
	if(username==null){
		ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401
	}
/*	User user=userDAO.getUserByUsername(username);
	if(!user.getRole().equals("ADMIN")){
		ErrorClazz error=new ErrorClazz(7,"Access Denied");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	} */
	else
		try{
		blogpost.updateBlogPost(blog);
		//return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		catch(Exception e){
			ErrorClazz error=new ErrorClazz(7,"unable to give approval for blogpost"+e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}

