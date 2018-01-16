package Vishnu.DAO;

import Vishnu.Model.Blog;
import Vishnu.Model.BlogLikes;
import Vishnu.Model.User;

public interface BlogLikesDao {
	
	BlogLikes userlikes(Blog blog,User user);
	Blog updatelikes(Blog blog,User user);
	  

}
