/**
 * 
 */

app.controller('FriendController',function($scope,$location,FriendService)
		{
	function listOfSuggestedUsers()
	{
		FriendService.listOfSuggestedUsers().then(function(response)
				{
			$scope.listofusers =response.data
		}
		,function(response)
		{
			if(response.status==401)
				$location.path('/login')
				//console.log(response.status)
		})
		
	}
	
	$scope.friendRequest=function(toId){
		FriendService.friendRequest(toId).then(function(response){
			alert("Request sent")
			listOfSuggestedUsers();
		$location.path('/suggesteduserslist')
	},function(response){
		if(response.status==401)
			$location.path('/login')
			console.log(response.status)
	})
	
	}
	
	listOfSuggestedUsers()
})