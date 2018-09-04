var app = angular.module('signout.controllers', []);

app.controller('signoutController', [ '$scope', '$rootScope', 'signoutService',
		'$location', function($scope, $rootScope, signoutService, $location) {
			
	signoutService.signout(

			function() {
				localStorage.clear();
				$rootScope.USER = null;
				$location.path("signin");
			}, function() {
				localStorage.clear();
				$rootScope.USER = null;
				$location.path("signin");
			});
	
		} ]);