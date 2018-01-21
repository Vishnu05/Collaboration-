package Vishnu.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Vishnu.DAO.UserDAO;
import Vishnu.Model.ErrorClazz;
import Vishnu.Model.User;

 
@Controller
public class UserController {
	@Autowired
	private UserDAO userDao;
	
	public UserController(){
		System.out.println("UserController is Instantiated");
	}
		@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){//5 
		try{
			if(!userDao.isUsernameValid(user.getUsername())){//
				ErrorClazz error=new ErrorClazz(2,"Username already exists..please choose different username");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
			}
			if(!userDao.isEmailValid(user.getEmail())){
				ErrorClazz error=new ErrorClazz(3,"EmailId already exists..please enter different email address");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
			}
		   userDao.registerUser(user);
		}catch(Exception e){
			ErrorClazz error=new ErrorClazz(1,"Unable to register user details");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		User validUser=userDao.login(user);
		if(validUser==null){
			ErrorClazz errorClazz=new ErrorClazz(4,"Invalid username/password");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//ErrorClazz,401
		}
		else{
			validUser.setOnline(true);
			session.setAttribute("username", validUser.getUsername());
			userDao.updateUser(validUser);
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){//without login
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401 -> login.html
		}
		User user=userDao.getUserByUsername(username);//select * from user where username=?
		user.setOnline(false);
		userDao.updateUser(user);//update User set online=false where username=?
		session.removeAttribute("username");
		session.invalidate();
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/getuser/{username}",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable String username ,HttpSession session){
		String usernames=(String)session.getAttribute("username");
		if(usernames==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);	
		}
	
		User getuser=userDao.getUserByUsername(username);
		return new ResponseEntity<User>(getuser,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/updateuser",method=RequestMethod.GET)
	public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session){
		if(session.getAttribute("username")==null){
    		ErrorClazz error=new ErrorClazz(5,"UnAuthorized User");
    		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
    	}
    	try{
    	userDao.updateUser(user);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    	}catch(Exception e){
    		ErrorClazz error=new ErrorClazz(6,"Unable to edit user profile "+ e.getMessage());
    		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

}
