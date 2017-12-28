/**
 * user controller
 */

 app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){

	
	$scope.registerUser=function(){
		console.log($scope.user)
		UserService.registerUser($scope.user)
		
		.then(function(response){
			$location.path('/login')
			alert('Regitration Success')
		},function(response){
			console.log(response.data)
			$scope.error=response.data  //ErrorClazz object in JSON
		})
	}
	
	$scope.login=function(){
		UserService.login($scope.user).then(function(response){
			$rootScope.currentUser=response.data
			$cookieStore.put('currentUser',response.data)
			$location.path('/home')
		},function(response){
			if(response.status==401){
				$scope.error=response.data//errorClazz in JSON fmt
				$location.path('/login')
			}
		})
	}
})