'use strict';

angular.module('app').config(function(RestangularProvider){
	RestangularProvider.setBaseUrl('http://localhost:8080');
	
    RestangularProvider.setResponseExtractor(function(response, operation) {
    	var extractedData;
      	for (var p1 in response) {
      		if ('_embedded' == p1) {
      			for (var p2 in response[p1]) {
      				if (response[p1].hasOwnProperty(p2)) {
  		      	      extractedData = response[p1][p2];
  		      	    }
      			}
      		}
      	}
      	
      	if (!extractedData) {
      		extractedData = response;
      	}
      	
      	return extractedData;
    });
})