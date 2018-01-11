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
				console.log(response.status)
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
	
	function listOfPendingRequests(){
		FriendService.listOfPendingRequests().then(function(response){
			$scope.pendingRequest=response.data;
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
	

	$scope.updatePendingRequest=function(pendingRequest,status){
	 
		console.log(pendingRequest.status)
		pendingRequest.status=status
		 
		FriendService.updatePendingRequest(pendingRequest).then(function(response){
			if(status=='A')
			{
			alert('You have accepted the friend request. You are now friends !');
			$location.path('/pendingrequests')
			}
			else
			{
			alert('You have denied the friend request')
			$location.path('/getAllUsers')
			}
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		
	})
	
	}		

	function listOfFriends(){
	FriendService.listOfFriends().then(function(response){
		$scope.friends=response.data
		console.log(response.status)
	},function(response){
		if(response.status==401)
			$location.path('/login')
		console.log(response.status)
	})
	}  
	
	
	
	listOfFriends() 
	listOfPendingRequests()
	listOfSuggestedUsers()
})