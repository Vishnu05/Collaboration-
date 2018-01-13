/**
 * Chat Service
 */

app.filter('reverse', function() {

	return function(items) {
		return items.slice().reverse();
	};
});

app.directive('ngFoucus', function() {
	return function(scope, element, attrs) {
		element.bind('click', function() {
			$('.' + attrs.ngFocus)[0].focus();
		});
	};
});

app.factory('socket', function($rootScope) {
	alert('app factory')
	var socket = new SockJS('/ProjectMiddleware/chatmodule');
	var stompClient = Stomp.over(socket);
	StompClient.connect('', '', function(frame) {
		$rootScope.$broadcast('sockConnected', frame);
	});


	return {
		stompClient : stompClient

	};
});