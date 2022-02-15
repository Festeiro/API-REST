'use strict'

var module = angular.module('myApp', []);

module.controller('AppController', function($http, $scope, $window){

	$scope.init = function(){

		$scope.entities = [
			'student', 'department', 'professor', 'project'
		]; 
		
		$scope.selectEntity = function(entity){
			
			$scope.selectedEntityPath = '/templates/' + entity + '/' + entity + '.html';
		};
		
		$scope.selectEntity($scope.entities[0]);
	};

});
