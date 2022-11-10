app.controller("account-ctrl",function($scope,$http){
	//alert("acoount")
	$scope.items =[];
	$scope.form = [];
	$scope.initialize = function(){
		//load product
		$http.get("/rest/accounts").then(resp =>{
			$scope.items = resp.data;
			$scope.reset();
		})

	}
	//khởi đầu
	$scope.initialize();
	//hiện lên form
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	//xóa form
	$scope.reset = function(){
		//alert("Xóa Form")
		$scope.form = {
		photo:'cloud-upload.jpg',}
	}
	//thêm account mới
	$scope.create = function(){
		//alert("Thêm sp")
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts/`,item).then(resp =>{
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
		}).catch(error =>{
			alert("Lỗi Thêm mới account")
			console.log("error",error);
		});
	}
	//cập nhật account
	$scope.update = function(){
		//alert("update sp")
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`,item).then(resp =>{
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			alert("cập nhật Account thành công");
		}).catch(error =>{
			alert("Lỗi cập nhật Account" + error)
			console.log("error",error);
		})
	}
	//xóa account
	$scope.delete = function(item){
		//alert("delete sp")
		$http.delete(`/rest/accounts/${item.username}`).then(resp =>{
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index , 1);
			$scope.reset();
			alert("Xóa Thành Công");
		}) 
		.catch(error =>{
			alert("Lỗi xóa Account!")
			console.log("Error" ,error);
		})
	}
	//upload hình
	$scope.imageChanged = function(files){
		//alert("hìn")
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/images',data,{
			transformRequest: angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp =>{
			$scope.form.photo = resp.data.name;
		}).catch(error =>{
			alert("Lỗi upload hình"+error);
			console.log("Error" ,error);
		})
	}
})