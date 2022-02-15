'use strict'

module.controller('ProjectController', function($filter, $http, $scope, $window, ProjectService, ProfessorService, ParticipatesService){
	
	$scope.projects = [{}];
	$scope.project = {};

	$scope.cols = [
		{name: 'projectNumber', widthInPercentage: '30' },
		{name: 'financier', widthInPercentage: '30' },
		{name: 'budget', widthInPercentage: '20'},
		{name: 'startDate', widthInPercentage: '10', isDate: true },
		{name: 'endDate', widthInPercentage: '20', isDate: true },
		{name: 'profLeaderRegAndName', widthInPercentage: '10'}
	];
	
	$scope.listProjects = function(){
				
		ProjectService.list().then(function(response){
		
			angular.forEach(response.data, function(project){
				project.startDate = new Date(project.startDate);				
				project.endDate = new Date(project.endDate);				
				if(project.profLeader && project.profLeader.reg_number){
					project.profLeaderRegAndName = project.profLeader.reg_number.toString() + ' - ' + project.profLeader.name; 
				}
			});
			$scope.projects = response.data;
		});
	};
	
	$scope.listProjects();
	
	$scope.save = function(){
		
		ProjectService.save($scope.project).then(function(response){			
			$scope.listProjects();
			$scope.projects = {};
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.update = function(project){
		
		$scope.project = angular.copy(project);
		resetParticipates();
		$scope.listParticipates();
	};
	
	$scope.delete = function(project){
		
		ProjectService.delete(project.projectNumber).then(function(response){			
			$scope.listProjects();
		},function(http, status){
			$window.alert("n deu boa" + status);
		});
	};
	
	// professors
	$scope.professors = [{}];
	ProfessorService.list().then(function(response){
		
		$scope.professors = response.data;
	});
	
	// participates
	$scope.participatess = [];
	$scope.participates = {};
	
	$scope.participatesCols = [
		{name: 'professorRegAndName', widthInPercentage: '20' },
	];
	
	var resetParticipates = function(){
		$scope.participates = { project: $scope.project };
	};
	
	$scope.listParticipates = function(){

		ParticipatesService.listByProjectNumber($scope.participates.project.projectNumber).then(function(response){
		
			$scope.participatess = response.data;
			angular.forEach($scope.participatess, function(participates){
				if(participates.professor && participates.professor.reg_number){
					
					participates.professorRegAndName = participates.professor.reg_number.toString() + ' - ' + participates.professor.name; 
				}
			});
		});
	};
		
	$scope.saveParticipates = function(){
		
		ParticipatesService.save($scope.participates).then(function(response){			
			$scope.listParticipates();
			resetParticipates();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.deleteParticipates = function(participates){
		
		ParticipatesService.delete(participates.project.projectNumber, participates.professor.reg_number).then(function(response){			
			$scope.listParticipates();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
});