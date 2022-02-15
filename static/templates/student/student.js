'use strict'

module.controller('StudentController', function($http, $scope, $window, StudentService, DepartmentService, ProjectService, AssistentService){
	
	$scope.students = [{}];
	$scope.student = {};
	$scope.projects = [{}];
	$scope.assist = {};
	$scope.assistents = [];
	
	$scope.courses = [
			'Computer Science',
			'Physics Bachelor',
			'Chemistry Bachelor',
			'Mechanical Engineering',
			'Production Engineering',
			'Civil Engineering'
	];
	
	$scope.cols = [
		{name: 'reg_number', widthInPercentage: '30' },
		{name: 'name', widthInPercentage: '30' },
		{name: 'age', widthInPercentage: '10' },
		{name: 'course', widthInPercentage: '20' },
		{name: 'advisorRegAndName', widthInPercentage: '20' },
		{name: 'departmentDepAndName', widthInPercentage: '20' }
	];
	
	$scope.listStudents = function(){
				
		StudentService.list().then(function(response){
		
			$scope.students = response.data;
			angular.forEach($scope.students, function(student){
				if(student.advisor && student.advisor.reg_number){
					
					student.advisorRegAndName = student.advisor.reg_number.toString() + ' - ' + student.advisor.name; 
				}
				if(student.department && student.department.dep_number){
					
					student.departmentDepAndName = student.department.dep_number.toString() + ' - ' + student.department.name; 
				}
			});
		});
	};
	
	$scope.listStudents();
	
	$scope.save = function(){
		StudentService.save($scope.student).then(function(response){
			$scope.listStudents();
			$scope.student = {};
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.update = function(student){
		
		$scope.student = angular.copy(student);
		resetAssist();
		$scope.listAssist();
	};
	
	$scope.delete = function(student){
		
		StudentService.delete(student.reg_number).then(function(response){			
			$scope.listStudents();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	DepartmentService.list().then(function(response){
		
		$scope.departments = response.data;
	});
	
	ProjectService.list().then(function(response){
		$scope.projects = response.data;
	})
	
	$scope.assistCols = [ {name: 'project_number', widthInPercentage: '20'} ];
		
	var resetAssist = function(){
		$scope.assistent = { student: $scope.student,
								project: $scope.project };
	};
	
	$scope.listAssist = function(){
		
		AssistentService.listByStudentRegNumber($scope.assistent.student.reg_number).then(function(response){
			
			$scope.assistents = response.data;
			angular.forEach($scope.assistents, function(assistent){
				if(assistent.project && assistent.project.projectNumber){
					assistent.project_number = assistent.project.projectNumber.toString();
				}
			});
		});
	};
	
	$scope.saveAssistent = function(){
		$scope.assistent.project = $scope.assist.project;
		AssistentService.save($scope.assistent).then(function(response){
			$scope.listAssist();
			resetAssist();			
		}, function(http, status){
			$window.alert("nao deu boa " + status);
		});	
	};
	
	$scope.deleteAssist = function(works){
		AssistentService.delete($scope.assistent.student.reg_number).then(function(response){
			$scope.listAssist();
		},function(http, status){
			$window.alert("nao deu boa " + status);
		});
	};
	
	
});