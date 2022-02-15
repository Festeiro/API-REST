'use strict'

module.service('ParticipatesService', function($http) {
	
	this.save = function(participates){
		
		return $http({
			method: 'POST',
			url: "http://localhost:8080/participates/save",
			data: participates,
		});
	};
	
	this.listByProjectNumber = function(projectNumber){
		return $http.get('/participates/listByProjectNumber', { params: {projectNumber: projectNumber}});
	};
	
	this.delete = function(projectNumber, professorRegNumber){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/participates/delete",
			params:{
				projectNumber: projectNumber,
				professorRegNumber: professorRegNumber
			}
		});
	};
});