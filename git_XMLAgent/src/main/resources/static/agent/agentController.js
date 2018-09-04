var app = angular.module('agent.controllers', []);

app.controller('agentController', ['$scope','$rootScope', 'agentService', 'signinService', '$location',
  	function ($scope, $rootScope, agentService, signinService, $location) {
	
	agentService.getUser($rootScope.USER.id, 
			function(response){
				if(response.data !== null && response.data !== ""){
					$scope.user = response.data
				}
			},
			function(response){
				
			}
			
	);
	
	
	$scope.signout = function() {
		$rootScope.USER = null;
		
		signinService.signout( 
				function(response){
					$location.path('signout');
				},
				function(response){
					
				});
	}	
	
}]);