/**
 * Blog Service 
 */

app.factory('BlogService',function($http){
	
	var blogService={}  //user defined name
	var BASE_URL="http://localhost:8085/ProjectMiddleware"
	blogService.saveBlog=function(blog){
	return  $http.post(BASE_URL+"/saveblog",blog)	
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get(BASE_URL + "/getblogs/"+1)
	}
	
	//select * from blogpost where approved=0
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get(BASE_URL + "/getblogs/"+0)
	}
	
	blogService.getBlogPost=function(id){
		return $http.get(BASE_URL + "/getblog/"+id)
	}

	return blogService;
	})
		
	
	
	

