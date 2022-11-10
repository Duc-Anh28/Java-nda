app.controller("category-ctrl", function($scope, $http) {
	//alert("category-ctrl")
	$scope.items = [];
	$scope.form = [];
	$scope.initialize = function() {
		//load product
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
		})

	}
	//khởi đầu
	$scope.initialize();

	//hiện lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	//xóa form
	$scope.reset = function() {
		//alert("Xóa Form")
		$scope.form = {}
	}
	//thêm category mới
	$scope.create = function() {
		//alert("Thêm sp")
		var item = angular.copy($scope.form);
		$http.post(`/rest/categories/`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			this.initialize();
			alert("Thêm Mớicategory Thành Công");
		}).catch(error => {
			alert("Lỗi Thêm Mới Category")
			console.log("error", error);
		});
	}
	//cập nhật category
	$scope.update = function() {
		//alert("update sp")
		var item = angular.copy($scope.form);
		$http.put(`/rest/categories/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập Nhật category Thành Công");
		}).catch(error => {
			alert("Lỗi cập nhật category" + error)
			console.log("error", error);
		})
	}
	//xóa category
	$scope.delete = function(item) {
		//alert("delete sp")
		$http.delete(`/rest/categories/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa category Thành Công");
		}).catch(error => {
				alert("Lỗi xóa category!")
				console.log("Error", error);
	   })
	}

	$scope.pager = {
		page: 0,
		size: 5,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

})