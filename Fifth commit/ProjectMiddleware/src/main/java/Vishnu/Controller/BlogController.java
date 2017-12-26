package Vishnu.Controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//		if (session.getAttribute("username") == null) {
//			ErrorClazz error = new ErrorClazz(5, "UnAuthorized User");
//			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
//		}

		String username = (String) session.getAttribute("username");
		 username="sriram";
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
}
