var app = angular.module("app",[]);
app.controller("ctrl",function($scope,$http){
	$http.get("/rest/autho").then(resp => {
		$scope.db = resp.data;
		console.log("data",resp.data)
	})
	
	$scope.index_of = function(username , role){
		return $scope.db.authorities
		.findIndex(ur => ur.account.username == username && ur.role.id == role);

	}
	
	$scope.update =  function(username , role){
		var index = $scope.index_of(username , role);
		if(index >=0){
			var id = $scope.db.authorities[index].id;
			$http.delete(`/rest/autho/${id}`).then(resp =>{
			   $scope.db.authorities.splice(index,1);
			   alert("thu hồi")
			})
		}else{
			var authority = {
				account:{username:username},
				role:{id:role}
			};$http.post(`/rest/autho`,authority).then(resp =>{
				$scope.db.authorities.push(resp.data);
				alert("phân quền")
			})
		}
	}
})