/**
 * 	blog controller
 */

app.controller('BlogController',function($scope,$location,BlogService,$rootScope){
	
	$scope.saveBlog=function(){
		BlogService.saveBlog($scope.blog).then(function(response){
			$location.path('/allblogs')
				alert('Blog Added successfully Waiting for the Admin Approval')
		},function(response){
			console.log(response.status)
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
			if(response.status==500){
				$scope.error=response.data
				$location.path('/home')
			}
			$location.path('/home')
		})
	}

	if($rootScope.currentUser.role=='ADMIN'){
		BlogService.getBlogsWaitingForApproval().then(function(response){
			$scope.blogsWaitingForApproval=response.data//select * from blogpost where approved=0
		},function(response){
			if(response.status==401){
				if(response.data.code==5)
				$location.path('/login')
				else{
					alert(response.data.message)
					$location.path('/home')
				}
			}
		})
		} 
		
	BlogService.getBlogsApproved().then(function(response){
		$scope.blogsApproved=response.data//select * from blogpost where approved=1
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	


	
})
