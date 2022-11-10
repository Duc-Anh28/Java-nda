const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {
	//	alert("AngularJS initialized")
	//   Quản lý giỏ hàng	
	$scope.cart = {
		items: [],
		add(id) {
			//alert(id)
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
				//alert("Thêm thành công")
			} else {
			   //alert("else")
				$http.get(`/rest/products/${id}`).then(resp =>{
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
					//alert("Thêm thành công")
				})
			}
		},
        
		//xóa 1 mặt hàng
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		//xóa hết giỏ hàng
		clear() {
			this.items = []
			this.saveToLocalStorage();
		},
		//tổng sô lượng các mặt hàng
		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		//tổng thành tiền các mặt hàng
		get amount() {
			return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
		},
		//lưu giỏ hàng vào Local Storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},

		//Đọc giỏ hàng từ Local Storage
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}
	
	
	$scope.cart.loadFromLocalStorage();
	
	
    $scope.timKiem = function() {
      //alert("timKiem cart")
      app.controller("product-ctrl")
    }
    
    
	$scope.order = {
		createDate: new Date(),
		address: "",
		account: { username: $("#username").text() },
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			});
		},
		purchanse() {
			var order = angular.copy(this);
			//thực hiện đặt hàng
			$http.post("/rest/order", order).then(resp => {
				alert("đặt hàng thành công");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("lỗi đặt hàng");
				console.log(error)
			})
		}
	}
})