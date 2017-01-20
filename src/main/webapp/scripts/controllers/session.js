'use strict';
angular.module('app').controller('sessionCtrl', 
		['$scope', '$state', '$auth', '$http', 'PermissionStore', '$rootScope', 'utilsService', 'toastr', '$q', '$interval', 'ParameterService',
function sessionCtrl($scope, $state, $auth, $http, PermissionStore, $rootScope, utilsService, toastr, $q,  $interval, ParameterService) {
	$scope.iamPermissions = [];
	$scope.iamRoles = [];
	$rootScope.authConsoleMessage = {};
	//load when controll is created.
	ParameterService.findAllByParamGroup('CONSOLE_AUTH_MESSAGE').then(function(response){
		angular.forEach(response.data._embedded.parameterInfo, function(value, key) {
			$rootScope.authConsoleMessage[value.paramCode] = value;
		});
	}).catch(function(response){
		console.log(response);
	});
	
	
	
	$scope.login = function(){
		toastr.clear();
		var isTokenValid = false;
		
		$auth.login($scope.user)
		  .then(function(response) {
		    // setting roles,objects after login success
			  PermissionStore.clearStore();
			  
			  isTokenValid = true;
			  $interval(function(){
				  utilsService.intervalServer().then(function(response){
					  isTokenValid = true;
				  }).catch(function(response){
					  isTokenValid = false;
				  });
			  }, 60*1000);
			  
			  PermissionStore.definePermission('isAuthenticated', function(){
				  return isTokenValid;
			  });
			  
			  utilsService.parseToken().then(function(response){
				  var objects = Object.keys(response.data.user.permission.objects);
				  var roles = response.data.user.permission.roles;
				  PermissionStore
	          		.defineManyPermissions(objects, function (permissionName, transitionProperties) {
	          			var result = isTokenValid;
	          			toastr.clear();//clear toast when page is changed.
	          			
	          			if (transitionProperties.toState.name.startsWith('app.param')) {
	          				result = result && permissionName=='MAP_MN_PARAMETER';
	          			}
	          			if (transitionProperties.toState.name.startsWith('app.systems')) {
	          				result = result && permissionName=='MAP_MN_SYSTEM';
	          			}
	          			if (result == false) {
	          				toastr.error($rootScope.authConsoleMessage['NO_OBJECTS'].descriptionEn, 'Error');
	          				$scope.authError();
	          			}
	          			
	          			return result;
	          		});	
				  
				  if (response.data.user.permission.objects['MAP_MN_PARAMETER'] || response.data.user.permission.objects['MAP_MN_SYSTEM']) {
					  toastr.clear();
					  $rootScope.userProfile = response.data.user.profile;
					  $state.go('app.systems');
				  } else {
					  toastr.error($rootScope.authConsoleMessage['NO_OBJECTS'].descriptionEn, 'Error');
					  $scope.authError();
				  }
				  
				  
			  });
		  })
		  .catch(function(response) {
			  toastr.error(response.data.message, 'Error');
			  console.error('ERROR',response.data.error_detail);
		  });
		};
		
	$scope.authError = function() {
		$state.go('user.signout');
	};
	
  $scope.myInterval = 5000;
  $scope.active = 0;
  var slides = $scope.slides = [];
  var currIndex = 0;

  $scope.addSlide = function() {
    var newWidth = 1200 + slides.length + 1;
    slides.push({
      image: 'http://lorempixel.com/' + newWidth + '/800',
      id: currIndex++
    });
  };
  for (var i = 0; i < 4; i++) {
    $scope.addSlide();
  }
}

]);