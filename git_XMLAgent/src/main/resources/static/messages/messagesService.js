var services = angular.module('messages.services', [ 'ngResource' ]);

services.service('messagesService', function($http) {
	
	return {
    	getSentMessages: function (idSender, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: 'http://localhost:9030/messages/sender/' + idSender,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
        getReceivedMessages: function (idReceiver, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: 'http://localhost:9030/messages/receiver/' + idReceiver,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req).then(onSuccess, onError);
        },
     
        messageSend : function (mess, onSuccess, onError) {
            var req = {
                    method: 'POST',
                    url: 'http://localhost:9030/messages/sendMessageTo',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data : mess
                };
                $http(req).then(onSuccess, onError);
            }
    }
	
});