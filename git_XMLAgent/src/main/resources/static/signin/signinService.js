var services = angular.module('signin.services', ['ngResource']);

services.service('signinService', ['$http', function($http){
	return {

		signin : function(user, onSuccess, onError) {
			var req = {
				method : 'POST',
				url : 'http://localhost:9030/authentication/signin',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : user
			};
			$http(req).then(onSuccess, onError);
		}
	}
	
}]);