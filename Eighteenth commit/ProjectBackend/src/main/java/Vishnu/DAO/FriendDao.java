package Vishnu.DAO;

import java.util.List;

import Vishnu.Model.Friend;
import Vishnu.Model.User;

public interface FriendDao {
	
	List<User> getListOfSuggestedUsers(String username);
	void addFriendRequest(String username,String toid);	
	List<Friend> getPendingRequests(String username);
	void updatePendingRequest(Friend pendingRequest);
	List<Friend> listOfFriends(String username);
}
