'use strict';
angular.module('app').controller('homeCtrl', ['$scope', '$http', '$localStorage', '$timeout', '$translate', '$auth', '$state' , '$stateParams', 'Restangular', 'toastr',
  function homeCtrl($scope, $http, $localStorage, $timeout, $translate, $auth, $state, $stateParams, Restangular, toastr) {
	
	$scope.$watch("init", function(){
		$scope.loadStations();
	});
	
	$scope.stations = {};
	
	$scope.loadStations = function(){
		var stationServices = Restangular.all('/api/stationses');
		
		stationServices.getList({size:1000}).then(function(response){
			$scope.stations = response;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.openModal = function(stationId){
		console.info('open station', stationId);
		$state.go('app.home.individual.config', {stationId:stationId});
	}
	
  }
]);