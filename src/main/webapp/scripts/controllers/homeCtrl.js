'use strict';
angular.module('app').controller('homeCtrl', ['$scope', '$http', '$localStorage', '$timeout', '$translate', '$auth', '$state' , '$stateParams', 
  function homeCtrl($scope, $http, $localStorage, $timeout, $translate, $auth, $state, $stateParams) {
	$scope.jobList = {	0:{	jobNo	:'DEP3166030800186',
							cifNo	:'00000000001',
							product	:'ซื้อบ้านมือสอง',
							name	:'น.ส. สอง',
							surname :'สามสี่ห้า',
							batchNo	:'88008181',
							date	:'22.07.2559',
							time	:'12:30'},
							
						1:{	jobNo	:'DEP3166030800187',
							cifNo	:'00000000002',
							product	:'ซื้อบ้านมือสอง',
							name	:'น.ส. สี่',
							surname :'สามสี่ห้า',
							batchNo	:'88008182',
							date	:'22.07.2559',
							time	:'12:30'},	
						2:{	jobNo	:'DEP3166030800188',
							cifNo	:'00000000003',
							product	:'ซื้อบ้านมือสอง',
							name	:'น.ส. แปด',
							surname :'สามสี่ห้า',
							batchNo	:'88008183',
							date	:'22.07.2559',
							time	:'12:30'},								
					  };
  }
]);