/**
 * Blog Service 
 */

app.factory('BlogService',function($http){
	
	var blogService={}  //user defined name
	
	blogService.saveBlog=function(blog){
	return  $http.post("http://localhost:8085/ProjectMiddleware/saveblog",blog)	
	}
	
	return blogService;
	})
		
	
	
	

