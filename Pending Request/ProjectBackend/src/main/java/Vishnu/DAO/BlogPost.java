package Vishnu.DAO;

import java.util.List;


import Vishnu.Model.Blog;
import Vishnu.Model.BlogComment;

public interface BlogPost {

	void saveblog(Blog blog); 
	List<Blog> getBlogs(int approved);
	Blog getbyBlogId(int id);
	void updateBlogPost(Blog blog, String rejectionReason);
	void addcomment(BlogComment blogcomment);
}

