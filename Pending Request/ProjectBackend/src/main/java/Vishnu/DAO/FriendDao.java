package Vishnu.DAO;

import java.util.List;

import Vishnu.Model.User;

public interface FriendDao {
	
	List<User> getListOfSuggestedUsers(String username);
	void addFriendRequest(String username,String toid);	

}
