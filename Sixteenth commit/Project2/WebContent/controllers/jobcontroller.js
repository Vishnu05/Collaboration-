/**
 * Job Controller
 */

app.controller('JobController', function(JobService, $scope, $location) {
	$scope.showJobDetails = false;

	function getAllJobs() {
		JobService.getAllJobs().then(function(response) {
			$scope.jobs = response.data;
			console.log("----------------------->" + $scope.jobs.id)
		}, function(response) {
			$location.path('/login')
		})
	}

	$scope.saveJob = function() {
		JobService.saveJob($scope.job).then(function(response) {
			alert('Job Added')
			$location.path('/getalljobs')
		}, function(response) {
			console.log(response.status)
			if (response.status == 401) {
				$scope.error = response.data
				$location.path('/login')
			}
			if (response.status == 500) {
				$scope.error = response.data
				$location.path('/savejob')
			}
			$location.path('/home')
		})
	}

	$scope.deleteJob = function(id) {
		JobService.deleteJob(id).then(function(response) {
			alert('Job Deleted Successfully to see changes refresh the page')
			$location.path('getalljobs')
			$scope.job = response.data
			
		}, function(resopnse) {
			console.log(response.data)
			$location.path('/login')
		})
	}

	$scope.getJobDetails = function(id) {
		$scope.showJobDetails = true
		JobService.getJobDetails(id).then(function(response) {
			$scope.job = response.data
		}, function(response) {
			console.log(response.data)
			$location.path('/login')
		})
	}

	getAllJobs()

})