/**
 * 
 */

app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL = "http://localhost:8085/ProjectMiddleware"
	
	friendService.listOfSuggestedUsers=function(){
	  return  $http.get(BASE_URL + "/getsuggestedusers")
	}
	friendService.friendRequest=function(toId){
		  return  $http.post(BASE_URL + "/friendRequest/"+toId)
		}
	return friendService;
	
})