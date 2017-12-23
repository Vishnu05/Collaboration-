/**
 * 	blog controller
 */

app.controller('BlogController',function($scope,$location,BlogService){
	$scope.saveBlog=function(){
		BlogService.saveBlog($scope.blog).then(function(response){
			$location.path('/blog')
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
	
})