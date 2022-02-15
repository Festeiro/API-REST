'use strict'

module.service('DepartmentService', function($http) {
	
	this.save = function(department){
		
		return $http({
			method: 'POST',
			url: "http://localhost:8080/department/save",
			data: department,
		});
	};
	
	this.list = function(){
		return $http.get('/department/list', {});
	};
	
	this.delete = function(id){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/department/delete",
			params:{
				id: id
			}
		});
	};
});