 /**
 * JobService
 */

app.factory('JobService',function($http){
	var jobService={}
	
	jobService.saveJob=function(job){
	  return  $http.post("http://localhost:8085/ProjectMiddleware/savejob",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("http://localhost:8085/ProjectMiddleware/getalljobs")
	}
	
	jobService.getJobDetails=function(id){
		return $http.get("http://localhost:8085/ProjectMiddleware/getjobbyid/"+id)
	}
	
	return jobService;
})