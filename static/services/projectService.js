'use strict'

module.service('ProjectService', function($http) {
	
	this.save = function(project){

		return $http({
			method: 'POST',
			url: "http://localhost:8080/project/save",
			data: project,
		});
	};
	
	this.list = function(){
		return $http.get('/project/list', {});
	};
	
	this.delete = function(id){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/project/delete",
			params:{
				id: id
			}
		});
	};
});