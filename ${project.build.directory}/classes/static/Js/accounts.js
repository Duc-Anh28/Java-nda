app.controller("account", function($scope, $http) {
	alert("account")
	$scope.ac = {
		clear() {
			alert("clear account")
		}
	}
})