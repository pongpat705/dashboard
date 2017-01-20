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
