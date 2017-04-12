
'use strict';
angular
	.module('app')
		.controller('edgeCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$auth', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope',
  function edgeCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, $auth, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope) {
	
	$scope.$watch("init", function(){
		$scope.loadEdges();
	});
	
	$scope.edges = [];
	
	$scope.loadEdges = function(){
		var edgeServices = Restangular.all('/dashboard/api/paths');
		
		edgeServices.getList({size:1000}).then(function(response){
			
			for (let r of response) {
				var dest = Restangular.one('/dashboard/api/stations',r.destId).get();
				var origin = Restangular.one('/dashboard/api/stations',r.originId).get();
				var edge = {
						'id' : r.id,
						'distance' : r.distance,
						'type' : r.type,
						'dest' : dest,
						'origin' : origin
				};
				
				$scope.edges.push(edge);
			}
			
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
			if (403 == response.status){
				$rootScope.unAuthorized();
			}
		});
	};
	
	$scope.openModal = function(pathId){
		$state.go('app.edge.individual.route', {pathId:pathId});
	}
	
  }
]);