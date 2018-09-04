var app = angular.module('messages.controllers', []);

app.controller('messagesController', [
	'$scope',
	'$rootScope',
	'messagesService',
	'agentService',
	'reservationService',
	'$location',
	function($scope, $rootScope, messagesService, agentService, reservationService, $location) {
		
		$scope.write = false;
		var res;
		
		$scope.respond = function(mess) {
			$scope.write = true;
			$scope.toWrite = mess.sender.username;
			res = mess;
		};

		$scope.sendMess = function(messageContent) {
			var message = {
				"senderId" : $rootScope.USER.id,
				"receiverId" : res.sender.id,
				"content" : messageContent
			}
			console.log(JSON.stringify(message));

			messagesService.messageSend(message, function(response) {
				$scope.write = false;
				alert("Message sent successfully!");

				$location.path('sentMessages');
			}, function(response) {
				alert("Greska");
			});

		};
		
		
		messagesService.getSentMessages($rootScope.USER.id,
				function(response){
					$scope.sentMessages = response.data;
				},
				function(response){
					alert("Greska");
				}
		);
		
		agentService.getUsers(
				function(response){

					$scope.users = response.data;
				},
				function(response){
					alert("Greska");
				}
		);
		
		messagesService.getReceivedMessages($rootScope.USER.id,
				function(response){

					$scope.receivedMessages = response.data;
				},
				function(response){
					alert("Greska");
				}
		);
	
		
		$scope.signout = function() {
			$rootScope.USER = null;
			$location.path('signout');
		}; 
	
} ]);