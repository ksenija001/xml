var services = angular.module('accommodationUnits.services', [ 'ngResource' ]);

services.service('accommodationUnitsService', function($http) {
	return {
		getAccommodations : function(agentUsername, onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/accommodations/getMyUnits/' + agentUsername,
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		},

		
		getAdditionalServices : function(onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/additionalServices',
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		},

		getCategoryTypes : function(onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/categoryTypes',
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		},

		
		
		getAccommodationTypes : function(onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/accommodationTypes',
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		},
		getTermins : function(accommodationId, onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/prices/priceForAccommodation/' + accommodationId,
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		},
		
		busyAccommodation : function(accommodationId, onSuccess, onError) {
			var req = {
				method : 'POST',
				url : 'http://localhost:9030/accommodations/busy/' + accommodationId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : accommodationId
			};
			$http(req).then(onSuccess, onError);
		}

	}
});