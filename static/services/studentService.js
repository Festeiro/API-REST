'use strict'

module.service('StudentService', function($http) {
	
	this.save = function(student){

		return $http({
			method: 'POST',
			url: "http://localhost:8080/student/save",
			data: student,
		});
	};
	
	this.list = function(){
		return $http.get('/student/list', {});
	};
	
	this.delete = function(id){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/student/delete",
			params:{
				id: id
			}
		});
	};
});