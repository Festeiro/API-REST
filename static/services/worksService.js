'use strict'

module.service('WorksService', function($http) {
	
	this.save = function(works){
		
		return $http({
			method: 'POST',
			url: "http://localhost:8080/works/save",
			data: works,
		});
	};
	
	this.listByProfessorRegNumber = function(professorRegNumber){
		return $http.get('/works/listByProfessorRegNumber', { params: {professorRegNumber: professorRegNumber}});
	};
	
	this.delete = function(professorRegNumber, departmentDepNumber){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/works/delete",
			params:{
				professorRegNumber: professorRegNumber,
				departmentDepNumber: departmentDepNumber 
			}
		});
	};
});