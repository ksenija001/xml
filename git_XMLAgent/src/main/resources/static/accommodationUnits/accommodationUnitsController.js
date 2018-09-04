var app = angular.module('accommodationUnits.controllers', []);

app.controller('accommodationUnitsController', [
		'$scope',
		'$rootScope',
		'accommodationService',
		'accommodationUnitsService',
		'$location',
		function($scope, $rootScope, accommodationService, accommodationUnitsService, $location) {
			$scope.searchCity = null;
			$scope.searchCountry = null;
			$scope.searchStartDate = null;
			$scope.searchEndDate = null;
			$scope.searchAdress = null;
			$scope.searchNumberOfPerson = null;
			$scope.selectedCategoryType = null;
			$scope.selectedAccommodationType = null;
			$scope.hideTableForTermins = true;

			accommodationUnitsService.getAccommodations($rootScope.USER.username, function(response) {
				$scope.accommodations = response.data;
			}, function(response) {
				alert("Greska");
			});

			accommodationUnitsService.getAdditionalServices(function(response) {
				$scope.additionalServices = response.data;
			}, function(response) {
				alert("Greska");
			});

			accommodationUnitsService.getCategoryTypes(function(response) {
				$scope.categoryTypes = response.data;
			}, function(response) {
				alert("Greska");
			});

			accommodationUnitsService.getAccommodationTypes(function(response) {
				$scope.accommodationTypes = response.data;
			}, function(response) {
				alert("Greska");
			});

			$scope.busyAccommodation = function(accommodationId) {
				accommodationUnitsService.busyAccommodation(accommodationId, 
						function(response) {
							$scope.accommodations = response.data;
							
							alert("Accommodation unit is busy now!");
							
				}, function(response) {
					alert("Ne valja");
				} );
			};

			

			$scope.details = function(accommodationId, accommodationDetail) {

				console.log($scope.searchStartDate + " datum "
						+ $scope.searchEndDate);

				$scope.detailForTerminAccommodation = accommodationDetail;
				$scope.hideTableForTermins = false;
				accommodationUnitsService.getTermins(accommodationDetail.id,
						function(response) {
							$scope.accommodationForTermins = response.data;
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