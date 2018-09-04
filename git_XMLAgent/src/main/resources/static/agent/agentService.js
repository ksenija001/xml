var services = angular.module('agent.services', [ 'ngResource' ]);

services.service('agentService', function($http) {
	return {
		getUser: function (idUser, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: 'http://localhost:9030/users/' + idUser,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
        
        getUsers: function (onSuccess, onError) {
            var req = {
                method: 'GET',
                url: 'http://localhost:9030/users/',
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
        
        signout: function (onSuccess, onError) {
            var req = {
                method: 'GET',
                url: 'http://localhost:9030/authentication/signout',
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        }
	}
});