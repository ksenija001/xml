var app = angular.module('reservation.controllers', []);

app.controller('reservationController', [
		'$scope',
		'$rootScope',
		'reservationService',
		'accommodationService',
		'$location',
		function($scope, $rootScope, reservationService, accommodationService, $location) 
		{
			reservationService.getReservations($rootScope.USER.username, function(
					response) {
				$scope.reservations = response.data;
			}, function(response) {
				alert("Greska")
			});

			$scope.confirmReservation = function(agentUsername, reservationId) {
				reservationService.confirmReservation(agentUsername, reservationId, function(
						response) {
					$scope.reservations = response.data;
					alert("Reservation confirmed");
				}, function(response) {
					alert("Greska");
				});
			};

			$scope.date = function(date) {
				var dat = new Date(date);
				return dat.toLocaleDateString();
			};

			
			
			$scope.signout = function() {
				$rootScope.USER = null;
				$location.path('signout');
			}

		} ]);