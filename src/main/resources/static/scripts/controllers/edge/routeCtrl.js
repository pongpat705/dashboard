'use strict';
angular
	.module('app')
		.controller('routeCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$auth', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope',
  function routeCtrl(	$scope, $http, $localStorage, 
		  				$timeout, $translate, $auth, 
		  				$state, $stateParams, Restangular, 
		  				toastr, $rootScope) {
	
	$scope.$watch("init", function(){
		$scope.loadStationList();
		$scope.loadPathDetail();
	});
	
	$scope.edge = {};
	$scope.nodes = [];
	$scope.pathId = $stateParams.pathId;

	$scope.loadStationList = function(){
		var nodeServices = Restangular.all('/dashboard/api/stations');
		nodeServices.getList({size:1000}).then(function(response){
			$scope.nodes = response;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
			if (403 == response.status){
				$rootScope.unAuthorized();
			}
		});
	};
	
	$scope.loadPathDetail = function(){
		var pathServices = Restangular.one('/dashboard/api/paths', $scope.pathId);
		
		pathServices.get().then(function(response){
			$scope.edge = response;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
			if (403 == response.status){
				$rootScope.unAuthorized();
			}
		});
	};
	
	$scope.savePath = function() {
		var pathServices = Restangular.one('/dashboard/api/paths', $scope.pathId);
		pathServices.patch($scope.edge).then(function(response){
			toastr.success('Update success');
			$scope.ok();
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
			if (403 == response.status){
				$rootScope.unAuthorized();
			}
		});
	};
	
	$scope.ok = function () {
		$state.go('app.edge',{},{reload:true});
	};
	
	$scope.cancel = function () {
		$state.go('app.edge',{},{reload:true});
	};	
	
  }
]);