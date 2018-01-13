/**
 * app.js
 */

var app = angular.module("app", [ 'ngRoute', 'ngCookies' ])
app.config(function($routeProvider) {
	$routeProvider.when('/registration', {
		templateUrl : 'views/registration.html',
		controller : 'UserController'
	})

	.when('/userprofile', {
		templateUrl : 'views/userprofile.html',
		controller: 'UserController'
	})
	
	.when('/login', {
		templateUrl : 'views/login.html',
		controller : 'UserController'
	})

	.when('/savejob', {
		templateUrl : 'views/jobform.html',
		controller : 'JobController'
	})
	
	.when('/getalljobs', {
		templateUrl : 'views/jobtitles.html',
		controller : 'JobController'
	})
	
	.when('/blog', {
		templateUrl : 'views/blog.html',
		controller : 'BlogController'
	})

	.when('/allblogs', {
		templateUrl : 'views/allblogs.html',
		controller : 'BlogController'
	})

	.when('/admin/getblog/:id', {
		templateUrl : 'views/blogapprove.html',
		controller : 'BlogDetails'
	})
	
	.when('/getblog/:id',{
		templateUrl : 'views/blogdetails.html',
		controller : 'BlogDetails'
	}) 
	
	.when('/listofuser',{
		templateUrl: 'views/searchforfriends.html',
		controller :'FriendController'
	})
	
	.when('/profile',{
		templateUrl: 'views/profilepic.html',
		controller:'Propic'
	})
	
	.when('/friendrequest',{
		templateUrl:'views/friendrequest.html',
		controller :'FriendController'
	})
	
	.when('/friendlist',{
		templateUrl:'views/friendlist.html',
		controller :'FriendController'
	})
	
	.otherwise({
		templateUrl : 'views/home.html'
	})
})
app.run(function($rootScope, $cookieStore, UserService, $location) {
	if ($rootScope.currentUser == undefined)
		$rootScope.currentUser = $cookieStore.get('currentUser')

	$rootScope.logout = function() {
		UserService.logout().then(function(response) {
			delete $rootScope.currentUser;
			$cookieStore.remove('currentUser')
			$location.path('/login')

		}, function(response) {
			console.log(response.status)
			$location.path('/login')
		})
	}
})
