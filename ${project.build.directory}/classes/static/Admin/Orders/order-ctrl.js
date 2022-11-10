app.controller("order-ctrl", function($scope, $http) {
	//alert("order-ctrl")
	$scope.items = [];
	$scope.form = [];
	$scope.initialize = function() {
		//load product
		$http.get("/rest/order").then(resp => {
			$scope.items = resp.data;
		})

	}
	//khởi đầu
	$scope.initialize();
	
	//hiện orderDetai
	$scope.edit = function(item) {
		var item = angular.copy(item);
		//alert("item" + item.id)
		$http.get(`/rest/OrderDetail/${item.id}`).then(resp => {
			$scope.form = resp.data;
			//alert("item" + resp.data)
		})
		$(".nav-tabs a:eq(0)").tab('show')
	}
	
	//xóa order
	$scope.delete = function(item){
		//alert("delete sp")
		$http.delete(`/rest/order/${item.id}`).then(resp =>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index , 1);
			alert("Xóa Thành Công");
		}) 
		.catch(error =>{
			alert("Lỗi xóa order!")
			console.log("Error" ,error);
		})
	}
	
	$scope.pager = {
		page: 0,
		size: 5,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
	}
})