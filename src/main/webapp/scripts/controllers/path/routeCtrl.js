'use strict';
angular
	.module('app')
		.controller('routeCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$auth', 
									'$state' , '$stateParams', '$rootScope',
  function routeCtrl(	$scope, $http, $localStorage, 
		  				$timeout, $translate, $auth, 
		  				$state, $stateParams, $rootScope) {
	$scope.stationId = $stateParams.stationId;
  }
]);