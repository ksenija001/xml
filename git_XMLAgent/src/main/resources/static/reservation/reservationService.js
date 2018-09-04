var services = angular.module('reservation.services', [ 'ngResource' ]);

services.service('reservationService', function($http) {
	return {
		getReservations : function(agentUsername, onSuccess, onError) {
			var req = {
				method : 'GET',
				url : 'http://localhost:9030/reservations/getReservationsOfMyUnits/' + agentUsername,
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http(req).then(onSuccess, onError);
		},
		
	
		confirmReservation : function(agentUsername, reservationId, onSuccess, onError) {
			var req = {
					method : 'GET',
					url : 'http://localhost:9030/reservations/confirmReservation/' + agentUsername + '/' + reservationId,
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http(req).then(onSuccess, onError);
		},
		
		

	}
});