'use strict';
angular.module('app').controller('configCtrl', ['$scope', '$http', '$localStorage', '$timeout', '$translate', '$auth', '$state' , '$stateParams', 'Restangular', 'toastr',
  function configCtrl($scope, $http, $localStorage, $timeout, $translate, $auth, $state, $stateParams, Restangular, toastr) {
	
	$scope.$watch("init", function(){
		$scope.loadStationDetail();
	});
	
	$scope.station = {};
	$scope.stationId = $stateParams.stationId;
	$scope.extendType = [	{	label:	'Yes',
						 		value:	1
							},
							{	label:	'No',
						 		value:	0
							},
						];
	
	$scope.loadStationDetail = function(){
		var stationServices = Restangular.one('stationses', $scope.stationId);
		
		stationServices.get().then(function(response){
			$scope.station = response;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.saveStation = function() {
		var stationServices = Restangular.one('stationses', $scope.stationId);
		stationServices.patch($scope.station).then(function(response){
			toastr.success('Update success');
			$scope.ok();
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.ok = function () {
		$state.go('app.home',{},{reload:true});
	};
	
	$scope.cancel = function () {
		$state.go('app.home',{},{reload:true});
	};	
	
  }
]);