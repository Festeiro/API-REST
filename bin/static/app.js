'use strict'

var module = angular.module('myApp', []);
module.controller('AppController', function($http, $scope, $window){
		
	$scope.userType = {
	    availableOptions: [
	      {id: '1', name: 'Student'},
	      {id: '2', name: 'Professor'}
		],
    selectedOption: {id: '1', name: 'Student'} //This sets the default value of the select in the ui
    };

	$scope.coursesList = {
		availableCourses: [
			{courseName: 'Computer Science'},
			{courseName: 'Physics Bachelor'},
			{courseName: 'Chemistry Bachelor'},
			{courseName: 'Mechanical Engineering'},
			{courseName: 'Production Engineering'},
			{courseName: 'Civil Engineering'}
		],
		course: {courseName: 'Computer Science'}
	};
	
	
	$scope.userDto = {
            name: null,
            age: null,
			selectedOption: null,
			classRoom: null,
			speciality: null,
			course: null
        };
	
	
	$scope.init = function(){
		$scope.list();		
	}
	
	$scope.saveUserAsStudent = function(userDto){
		$http({
			method: 'POST',
			url: "http://localhost:8080/student",
			data: {
				name: userDto.name,
				age: userDto.age,
				course: userDto.course.courseName
				
			}
		}).success(function(response){			
			$scope.list();
		}).error(function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.saveUserAsProfessor = function(userDto){
		$http({
			method: 'POST',
			url: "http://localhost:8080/professor",
			data: {
				name: userDto.name,
				age: userDto.age,
				classRoom: userDto.classRoom,
				speciality: userDto.speciality,
				
			}
		}).success(function(response){			
			$scope.list();
		}).error(function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.deleteUser = function(user){		
		$http({
			method: 'DELETE',
			url: "http://localhost:8080/delete",
			params: { id: user.delId }
		}).then(function(response){
			$scope.list();
			$window.alert("Removing...");
		});
	};
	
	$scope.list = function(){
		$http.get('/user/list', {}).then(function(response){
			//console.log(response.data);
			$scope.users = response.data;				
		});
	};
	
	$scope.searchUser = function(user){	
		$http.get('/user',{					
			params: {
				id: user.id,
				name: user.name
			}
		}).then(function(response){
			console.log("encontrado: ", response.data)
			$scope.userFound = response.data
		});
	};
});
