'use strict';

angular.module(
		'routerApp',
		[ 'ui.router', 'signin.services', 'signin.controllers', 
			'signout.controllers', 'signout.services',
			'agent.services', 'agent.controllers',
			'accommodation.services', 'accommodation.controllers',
			'accommodationUnits.services', 'accommodationUnits.controllers',
			'messages.services', 'messages.controllers',
			'reservation.services', 'reservation.controllers'
				]).config(
		function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise('/signin');

			$stateProvider
			// AUTHENTICATION
			.state('signin', {
				url : '/signin',
				templateUrl : 'signin/signin.html',
				controller : 'signinController'
			})

			.state('signout', {
				url : '/signout',
				templateUrl : 'signout/signout.html',
				controller : 'signoutController'
			})
			
			.state('agent', {
				url : '/agent',
				templateUrl : 'agent/agent.html',
				controller : 'agentController'
			})
			
			.state('accommodation', {
				url : '/accommodation',
				templateUrl : 'accommodation/accommodation.html',
				controller : 'accommodationController'
			})
			
			.state('accommodationUnits', {
				url : '/accommodationUnits',
				templateUrl : 'accommodationUnits/accommodationUnits.html',
				controller : 'accommodationUnitsController'
			})
			
			.state('messages', {
				url : '/messages',
				templateUrl : 'messages/messages.html',
				controller : 'messagesController'
			})
			
			.state('receivedMessages', {
				url : '/receivedMessages',
				templateUrl : 'messages/receivedMessages.html',
				controller : 'messagesController'
			})
      
			.state('sentMessages', {
				url : '/sentMessages',
				templateUrl : 'messages/sentMessages.html',
				controller : 'messagesController'
			})
			
			.state('reservation', {
				url : '/reservation',
				templateUrl : 'reservation/reservation.html',
				controller : 'reservationController'
			})
			
			

		}).run(["$rootScope", "$state", function ($rootScope,$state) {
			//console.log(!angular.isDefined($rootScope.USER));
			//console.log(JSON.parse(localStorage.getItem("user")));
			
		    if(!angular.isDefined($rootScope.USER) && localStorage.getItem("user")){
		        // UserInfo exists in localstorate but not on $rootScope. This means the page was reloaded or the user is returning.
		        $rootScope.USER = JSON.parse(localStorage.getItem("user"));
		        //console.log(localStorage.getItem("user"));

		    }else if(!angular.isDefined($rootScope.USER) && !localStorage.getItem("user")){
		        // User is not logged at all. Send him back to login page
		    	localStorage.clear();
		    	$state.go("signin");
		    
		    }else if(angular.isDefined($rootScope.USER)){
		        // User is logged in. You can run some extra validations in here.
		    }
			
		    }]);
