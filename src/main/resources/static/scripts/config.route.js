'use strict';
angular.module('app').run(['$rootScope', '$state', '$stateParams',
  function($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.$on('$stateChangeSuccess', function() {
      window.scrollTo(0, 0);
    });
    FastClick.attach(document.body);
  },
]).config(['$stateProvider', '$urlRouterProvider', '$injector',
  function($stateProvider, $urlRouterProvider, $injector) {
    $urlRouterProvider.otherwise( function($injector) {
    	var $state = $injector.get("$state");
    	$state.go('app.home');
    });
    
    // Application routes
    $stateProvider.state('app', {
        abstract: true,
        templateUrl: './views/common/layout.html',
        data: {
            permissions: {
              only: ['ROLE_ADMIN'],
              redirectTo: 'user.signin'
            }
          }
      })
        .state('app.node', {
            url: '/node',
            templateUrl: './views/app/node/node.html',
            controller: 'nodeCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                              './scripts/controllers/node/nodeCtrl.js'
                              ]
                    }]);
                }]
              }
        })     
      .state('app.node.individual',{
      	url: '/{stationId:int}',
      	onEnter:['$uibModal', '$state', '$stateParams', function($uibModal, $state, $stateParams){
      		$uibModal.open({
      			template : '<div ui-view="modal"></div>',
      			size: 'lg'
      		}).result.finally(function(){
      			$state.go('app.node',{},{reload:true});
      		});
      	}],
    	resolve: {
            deps: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load([{
                  files: [	'./scripts/controllers/node/configCtrl.js'
                	  		]
                }]);
            }]
          }
      })
      .state('app.node.individual.config',{
      	url: '/config',
      	views:{
      		'modal@':{
      			templateUrl:'views/app/node/config.html',
      			controller: 'configCtrl'
      		}
      	}
      })
        .state('app.edge', {
            url: '/edge',
            templateUrl: './views/app/edge/edge.html',
            controller: 'edgeCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                              './scripts/controllers/edge/edgeCtrl.js'
                              ]
                    }]);
                }]
              }
        })
        .state('app.edge.individual',{
	      	url: '/{pathId:int}',
	      	onEnter:['$uibModal', '$state', '$stateParams', function($uibModal, $state, $stateParams){
	      		$uibModal.open({
	      			template : '<div ui-view="modal"></div>',
	      			size: 'lg'
	      		}).result.finally(function(){
	      			$state.go('app.edge',{},{reload:true});
	      		});
	      	}],
	    	resolve: {
	            deps: ['$ocLazyLoad', function($ocLazyLoad) {
	              return $ocLazyLoad.load([{
	                  files: [	'./scripts/controllers/edge/routeCtrl.js'
	                	  		]
	                }]);
	            }]
	          }
      })
      .state('app.edge.individual.config',{
      	url: '/config',
      	views:{
      		'modal@':{
      			templateUrl:'views/app/edge/config.html',
      			controller: 'routeCtrl'
      		}
      	}
      })
        .state('app.parameters',{
        	url: '/parameters',
            templateUrl: './views/app/parameters/all.html',
            controller: 'paramCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                              './scripts/controllers/parameters/paramCtrl.js'
                              ]
                    }]);
                }]
              }
        })
      .state('user', {
        templateUrl: './views/common/session.html',
      }).state('user.signin', {
        url: '/signin',
        templateUrl: './views/signin.html',
        controller:'sessionCtrl',
        resolve: {
          deps: ['$ocLazyLoad', function($ocLazyLoad) {
            return $ocLazyLoad.load('./scripts/controllers/session.js');
          }]
        },
        data: {
          appClasses: 'signin usersession',
          contentClasses: 'session-wrapper'
        }
      }).state('user.signout',{
    	  url:'/signout',
    	  templateUrl:'./views/signin.html',
    	  controller:'logoutCtrl',
          resolve: {
              deps: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load('./scripts/controllers/logout.js');
              }]
            },
            data: {
              appClasses: 'signin usersession',
              contentClasses: 'session-wrapper'
            }
      })
      
     ;
  }
]).config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
  $ocLazyLoadProvider.config({
    debug: false,
    events: false
  });
}]);
