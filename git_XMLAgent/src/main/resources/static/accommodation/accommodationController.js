var app = angular.module('accommodation.controllers', []);

app.controller('accommodationController', [
	'$scope',
	'$rootScope',
	'accommodationService',
	'$location',
	function($scope, $rootScope, accommodationService, $location) {
		$scope.rating = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ];
		$scope.selectedCategoryType = null;
		$scope.selectedAccommodationType = null;
		
		$scope.createAccommodation = function () {
			accommodationService.createAccommodation($scope.accommodation,
					function(response) {
				
					alert("Successfully created accommodation!");
					
					},

					function(response) {
						alert(response.data.error);
					});
		};
		
		accommodationService.getAdditionalServices(function(response) {
			$scope.additionalServices = response.data;
		}, function(response) {
			alert("Greska");
		});

		accommodationService.getCategoryTypes(function(response) {
			$scope.categoryTypes = response.data;
		}, function(response) {
			alert("Greska");
		});
		
		accommodationService.getAccommodationTypes(function(response) {
			$scope.accommodationTypes = response.data;
		}, function(response) {
			alert("Greska");
		});
		
		$scope.setFile = function(element) {
			  $scope.currentFile = element.files[0];
			   var reader = new FileReader();

			  reader.onload = function(event) {
			    $scope.image_source = event.target.result
			    $scope.$apply()

			  }
			  // when the file is read it triggers the onload event above.
			  reader.readAsDataURL(element.files[0]);
			}
		
		
		$scope.signout = function() {
			$rootScope.USER = null;
			$location.path('signout');
		}; 
	
} ]);