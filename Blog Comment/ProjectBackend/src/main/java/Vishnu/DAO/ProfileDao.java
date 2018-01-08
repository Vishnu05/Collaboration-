package Vishnu.DAO;

import Vishnu.Model.ProfilePic;

public interface ProfileDao {

	void saveOrUpdate(ProfilePic profilePic);
	ProfilePic getprofilepic(String username);
	
}
