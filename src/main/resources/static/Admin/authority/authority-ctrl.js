app.controller("authority-ctrl",function($scope,$http,$location){
    //alert("authority-ctrl")
    $scope.db = [];

    $scope.initialize = function(){
		$http.get("/rest/autho").then(resp => {
			$scope.db = resp.data;
			console.log("data", resp.data)
		})
    }
    //khởi đầu
	$scope.initialize();
	
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
			   alert("Thu Hồi")
			})
		}else{
			var authority = {
				account:{username:username},
				role:{id:role}
			};$http.post(`/rest/autho`,authority).then(resp =>{
				$scope.db.authorities.push(resp.data);
				alert("Phân Quyền")
			})
		}
	}
	
})