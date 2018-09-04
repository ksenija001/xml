var services = angular.module('accommodation.services', [ 'ngResource' ]);

services.service('accommodationService', function($http) {
	return {
		createAccommodation : function(accommodation, onSuccess, onError) {
			var req = {
				method : 'POST',
				url : 'http://localhost:9030/accommodations' ,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : accommodation
			};
			$http(req).then(onSuccess, onError);
		},
		
		getAccommodations : function(onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/accommodations',
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
		

	}
});