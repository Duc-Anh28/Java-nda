app.controller("product-ctrl",function($scope,$http){
	//alert("Quản lý Product")
	$scope.items =[];
	$scope.form = [];
	$scope.cates = [];
	
	$scope.newValue = function(value) {
	    alert("Radio " + value)
		//console.log(value);
	}
	
	$scope.initialize = function(){
		//load product
		$http.get("/rest/products").then(resp =>{
			$scope.items = resp.data;
			$scope.items.forEach(item =>{
				item.createDate = new Date(item.createDate)
			})
			$scope.reset();
		})
		//load category
		$http.get("/rest/categories").then(resp =>{
		 $scope.cates = resp.data;
		})
		
	}
	//khởi đầu
	$scope.initialize();
	//xóa form
	$scope.reset = function(){
		//alert("Xóa Form")
		$scope.form = {
			createDate: new Date(),
			image:'cloud-upload.jpg',
			available:true,
		}
	}
	//hiện lên form
	$scope.edit = function(item){
	  alert("Xóa Form" + item)
		$scope.form = angular.copy(item);
		
		$(".nav-tabs a:eq(0)").tab('show')
	}
	
	$scope.onkeyup = function() {
	    var keyword = document.getElementById("keyword").value;
	    alert("Tìm Kiếm:" + keyword)
	}
	///tìm kiếm
	$scope.timKiem = function() {
	     var keyword = document.getElementById("keyword").value;
	     //alert("Tìm Kiếm:" + keyword)
	     if(keyword == ""){
			$http.get("/rest/products").then(resp =>{
				$scope.items = resp.data;
				$scope.items.forEach(item =>{
					item.createDate = new Date(item.createDate)
		 		})
				$scope.reset();
			})
		 }else{
			$http.get(`/rest/products/kw/${keyword}`).then(resp =>{
				$scope.items = resp.data;
				$scope.items.forEach(item =>{
					item.createDate = new Date(item.createDate)
				})
			})
		 }
		 
	}
		
	//Thêm mới
	$scope.create = function() {
		//alert("Create")
		var item = angular.copy($scope.form);
		alert("Create " + item.createDate) 
//		$http.post(`/rest/products`, item).then(resp => {
//			resp.data.createDate = new Date(resp.data.createDate)
//			$scope.items.push(resp.data);
//			//$scope.reset();
//			$scope.initialize();
//			alert("Thêm mới sản phẩm thành công!");
//		}).catch(error => {
//			alert("Thêm mới thất bại");
//			console.log("Lỗi nè===:", error);
//		});
	}

	//cập nhật sp
	$scope.update = function(){
		//alert("update sp")
		var item = angular.copy($scope.form);
        alert("id " + item.createDate); 
//		$http.put(`/rest/products/${item.id}`,item).then(resp =>{
//			var index = $scope.items.findIndex(p => p.id == item.id);
//			$scope.items[index] = item;
//			alert("cập nhật thành công");
//		}).catch(error =>{
//			alert("Lỗi cập nhật sp" + error)
//			console.log("error",error);
//		})
	}
	//xóa sp
	$scope.delete = function(item){
		//alert("delete sp")
		$http.delete(`/rest/products/${item.id}`).then(resp =>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index , 1);
			$scope.reset();
			alert("Xóa Thành Công");
		})
		.catch(error =>{
			alert("Lỗi xóa!")
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
			$scope.form.image = resp.data.name;
		}).catch(error =>{
			alert("Lỗi upload hình"+error);
			console.log("Error" ,error);
		})
	}
	
	$scope.pager = {
		page: 0,
		size: 8,
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
});