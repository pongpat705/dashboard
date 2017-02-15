'use strict';
angular.module('app').config(function($authProvider) {
	$authProvider.baseUrl 		= '/dashboard';
	$authProvider.loginUrl 		= '/login';
	$authProvider.tokenName 	= 'token';
	$authProvider.authHeader  	= 'maoz-token';
	$authProvider.authToken 	= 'Bearer';

}).run(function($auth, PermissionStore){
	$auth.setStorageType('sessionStorage');
	if ( $auth.isAuthenticated() ){
		PermissionStore
	        .definePermission('isAuthenticated', function () {
	          return true;
	        });
	} 
});