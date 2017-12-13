package Vishnu.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
//client -Angular JS Client
//User - in JSON
//convert JSON to java object
// ? any type, for success Type is User, for error Type is ErrorClazz
@RequestMapping(value="/registeruser",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody User user){//5
	try{
		if(!userDao.isUsernameValid(user.getUsername())){//Duplicate Username
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
		//failure - response.data=error, response.status=500			
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
}




}