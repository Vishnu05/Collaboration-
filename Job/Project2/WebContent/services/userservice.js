 /**
 * UserService
 */
app.factory('UserService',function($http){
	
	var BASE_URL="http://localhost:8085/ProjectMiddleware"
	var userService={}
	
		userService.registerUser=function(user){
	 	console.log(user)
		return $http.post(BASE_URL + "/registeruser",user)//4
		//8
	}
	
	userService.login=function(user){
		return $http.post(BASE_URL + "/login",user)
	}
	
	userService.logout=function(){
		return $http.get(BASE_URL + "/logout")
	}
	return userService;
})