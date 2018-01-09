package Vishnu.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Vishnu.DAO.FriendDao;
import Vishnu.DAO.UserDAO;
import Vishnu.Model.ErrorClazz;
import Vishnu.Model.User;

@RestController
public class FriendController {

	@Autowired
	private FriendDao friendD;

	@Autowired
	private UserDAO userD;

/**	@RequestMapping(value = "/getsuggestedusers", method = RequestMethod.GET)
	public ResponseEntity<?> getListOfSuggestedUsers(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username== null) {
			ErrorClazz error = new ErrorClazz(5, "UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}		
		List<User> suggestUsers = friendD.getListOfSuggestedUsers(username);
		return new ResponseEntity<List<User>>(suggestUsers, HttpStatus.OK);
	}
*/
	@RequestMapping(value = "/friendRequest/{toId}", method = RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@PathVariable String toId, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username== null) {
			ErrorClazz error = new ErrorClazz(5, "UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}		
		friendD.addFriendRequest(username, toId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
