'use strict';
angular
	.module('app')
		.controller('serviceCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$auth', 
									'$state' , '$stateParams', '$rootScope',
									'Restangular', 'toastr',
  function serviceCtrl(	$scope, $http, $localStorage, 
		  				$timeout, $translate, $auth, 
		  				$state, $stateParams, $rootScope,
		  				Restangular, toastr) {
		
	$scope.$watch("init", function(){
		$scope.loadStationList();
		$scope.type = 	[
							{	label 	: 'shortcut',
								value	: 1
							},
							{	label 	: 'exchange',
								value	: 2
							},
						];
	});
	
	$scope.stations = [];
	
	$scope.shortestPathBean = {
			origin : {},
			destination : {},
			type : 1,
	}
	
	$scope.loadStationList = function(){
		var stationServices = Restangular.all('/dashboard/api/stations');
		
		stationServices.getList({size:1000}).then(function(response){
			$scope.stations = response;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
			if (403 == response.status){
				$rootScope.unAuthorized();
			}
		});
	};
	
	$scope.getShortesPath = function(){
		var getPathService  = Restangular.all('/dashboard/service/findpath');
		getPathService.post($scope.shortestPathBean).then(function(response){
			console.log(response);
		}).catch(function(response){
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
			if (403 == response.status){
				$rootScope.unAuthorized();
			}
		})
	};
	
	$scope.clear = function(){
		
		$scope.shortestPathBean.origin = {};
		$scope.shortestPathBean.destination = {};
		$scope.shortestPathBean.type = 1;
		
	};
	
  }
]);