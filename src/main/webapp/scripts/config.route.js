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
    // For unmatched routes
//	 $urlRouterProvider.otherwise('/home');
    $urlRouterProvider.otherwise( function($injector) {
    	var $state = $injector.get("$state");
    	$state.go('app.home');
    });
    
    // Application routes
    $stateProvider.state('app', {
        abstract: true,
        templateUrl: './views/common/layout.html',
//        data: {
//            permissions: {
//              only: ['isAuthenticated'],
//              redirectTo: 'user.signin'
//            }
//          }
      })
        .state('app.home', {
            url: '/home',
            templateUrl: './views/app/home.html',
            controller: 'homeCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                              './scripts/controllers/homeCtrl.js'
                              ]
                    }]);
                }]
              }
        })     
      .state('app.home.individual',{
      	url: '/{stationId:int}',
      	onEnter:['$uibModal', '$state', '$stateParams', function($uibModal, $state, $stateParams){
      		$uibModal.open({
      			template : '<div ui-view="modal"></div>',
      			size: 'lg'
      		}).result.finally(function(){
      			$state.go('app.home',{},{reload:true});
      		});
      	}],
    	resolve: {
            deps: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load([{
                  files: [	'./scripts/controllers/path/configCtrl.js',
                	  		'./scripts/controllers/path/routeCtrl.js'
                	  		]
                }]);
            }]
          }
      })
      .state('app.home.individual.config',{
      	url: '/config',
      	views:{
      		'modal@':{
      			templateUrl:'views/app/path/config.html',
      			controller: 'configCtrl'
      		}
      	}
      })
      .state('app.home.individual.route',{
      	url: '/route',
      	views:{
      		'modal@':{
      			templateUrl:'views/app/path/route.html',
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
//        controller:'sessionCtrl',
//        resolve: {
//          deps: ['$ocLazyLoad', function($ocLazyLoad) {
//            return $ocLazyLoad.load('scripts/controllers/session.js');
//          }]
//        },
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
