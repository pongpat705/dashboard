angular
	.module('app')
		.controller('logoutCtrl',[	'$scope', '$state', '$auth', 
									'$http', 'PermissionStore', '$rootScope', 
	function LogoutCtrl($scope, $state, $auth, 
						$http, PermissionStore, $rootScope) {
    $auth.logout()
      .then(function() {
    	  PermissionStore.clearStore();
    	  $state.go('user.signin');
      });

}]);