'use strict'

module.controller('DepartmentController', function($http, $scope, $window, DepartmentService, ProfessorService){

	$scope.departments = [{}];
	$scope.department = {};
	$scope.professors = [{}];
	
	$scope.cols = [
		{name: 'dep_number', widthInPercentage: '30' },
		{name: 'name', widthInPercentage: '30' },
		{name: 'centralOffice', widthInPercentage: '10' },
		{name: 'profLeaderRegAndName', widthInPercentage: '10' },
	];
	
	$scope.listDepartments = function(){

		DepartmentService.list().then(function(response){
		
			$scope.departments = response.data;
			angular.forEach($scope.departments, function(department){
				if(department.profLeader && department.profLeader.reg_number){
					
					department.profLeaderRegAndName = department.profLeader.reg_number.toString() + ' - ' + department.profLeader.name; 
				}
			});
		});
	};
	
	$scope.listDepartments();
	
	$scope.save = function(){
		
		DepartmentService.save($scope.department).then(function(response){			
			$scope.listDepartments();
			$scope.department = {};
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.update = function(department){
		
		$scope.department = angular.copy(department);
	};
	
	$scope.delete = function(department){
		
		DepartmentService.delete(department.dep_number).then(function(response){			
			$scope.listDepartments();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	ProfessorService.list().then(function(response){
		
		$scope.professors = response.data;
	});
	
});