var app = angular.module('signin.controllers', []);

app.controller('signinController', ['$scope','$rootScope', 'signinService', '$location',
  	function ($scope, $rootScope, signinService, $location) {
	
	$scope.signin = function() {
		signinService.signin($scope.user, function(response){
				
							$scope.state = undefined;
							if (response.data !== null
									&& response.data !== "") {
								$rootScope.USER = response.data;
								console.log("Logovan je "
										+ $rootScope.USER.id + " rola "
										+ $rootScope.USER.role);
								if ($rootScope.USER.role == 'AGENT') {
									console.log("Agent");
									localStorage.setItem("user", JSON.stringify($rootScope.USER));
									$location.path('agent');
								} else {
									alert("Please, log in as agent.");
									$rootScope.USER = null;
								}
							} else {
								 alert("User must be activated.");
							}
						}, function(response) {
							 alert("User must be activated.");
						});
	}
		
		
}]);