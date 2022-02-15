'use strict'

module.service('AssistentService', function($http) {
	
	this.save = function(assistent){
		
		return $http({
			method: 'POST',
			url: "http://localhost:8080/assist/save",
			data: assistent,
		});
	};
	
	this.listByStudentRegNumber = function(studentRegNumber){
		return $http.get('/assist/listAssistents', { params: {studentRegNumber: studentRegNumber}});
	};
	
	this.delete = function(studentRegNumber, projectNumber){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/assist/delete",
			params:{studentRegNumber: studentRegNumber}
		});
	};
});