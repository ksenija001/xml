var services = angular.module('signout.services', [ 'ngResource' ]);

services.service('signoutService', function($http) {
	return {
		signout : function(onSuccess, onError) {

			var req = {
				method : 'GET',
				url : 'http://localhost:9030/authentication/signout',
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		}

	}
});