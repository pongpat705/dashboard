'use strict';
angular.module('app').controller('routeCtrl', ['$scope', '$http', '$localStorage', '$timeout', '$translate', '$auth', '$state' , '$stateParams', 
  function routeCtrl($scope, $http, $localStorage, $timeout, $translate, $auth, $state, $stateParams) {
	$scope.stationId = $stateParams.stationId;
  }
]);