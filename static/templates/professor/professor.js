'use strict'

module.controller('ProfessorController', function($http, $scope, $window, ProfessorService, WorksService, DepartmentService){
	
	$scope.profs = [{}];
	$scope.professor = {};

	$scope.cols = [
		{name: 'reg_number', widthInPercentage: '30' },
		{name: 'name', widthInPercentage: '30' },
		{name: 'age', widthInPercentage: '10' },
		{name: 'speciality', widthInPercentage: '20' },
		{name: 'classRoom', widthInPercentage: '20' }
	];
	
	$scope.listProfs = function(){
				
		ProfessorService.list().then(function(response){
		
			$scope.profs = response.data;
		});
	};
	
	$scope.listProfs();
	
	var resetProfessor = function(){
		$scope.professor = {};
		$scope.works = {};
		$scope.workss = [];
	};
	
	$scope.save = function(){
		
		ProfessorService.save($scope.professor).then(function(response){			
			$scope.listProfs();
			resetProfessor();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.update = function(professor){
		
		$scope.professor = angular.copy(professor);
		resetWorks();
		$scope.listWorks();
	};
	
	$scope.delete = function(professor){
		
		ProfessorService.delete(professor.reg_number).then(function(response){			
			$scope.listProfs();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	// departments
	$scope.departments = [{}];
	DepartmentService.list().then(function(response){
		
		$scope.departments = response.data;
	});
	
	// works
	$scope.workss = [];
	$scope.works = {};
	
	$scope.worksCols = [
		{name: 'departmentDepAndName', widthInPercentage: '20' },
		{name: 'timePercentage', widthInPercentage: '20' },
	];
	
	var resetWorks = function(){
		$scope.works = { professor: $scope.professor };
	};
	
	$scope.listWorks = function(){

		WorksService.listByProfessorRegNumber($scope.works.professor.reg_number).then(function(response){
		
			$scope.workss = response.data;
			angular.forEach($scope.workss, function(works){
				if(works.department && works.department.dep_number){
					
					works.departmentDepAndName = works.department.dep_number.toString() + ' - ' + works.department.name; 
				}
			});
		});
	};
		
	$scope.saveWorks = function(){
		
		WorksService.save($scope.works).then(function(response){			
			$scope.listWorks();
			resetWorks();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.deleteWorks = function(works){
		
		WorksService.delete(works.professor.reg_number, works.department.dep_number).then(function(response){			
			$scope.listWorks();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
});