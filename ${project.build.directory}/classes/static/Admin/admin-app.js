app = angular.module("admin-app",["ngRoute"]);
app.config(function ($routeProvider){
	$routeProvider
	.when("/product",{
		templateUrl:"/Admin/Product/index.html",
		controller:"product-ctrl"
	})
	.when("/category",{
		templateUrl:"/Admin/Category/index.html",
		controller:"category-ctrl"
	})
	.when("/authorize",{
		 templateUrl:"/Admin/authority/index.html",
		 controller:"authority-ctrl"
	})
	.when("/account",{
		templateUrl:"/Admin/Account/index.html",
		controller:"account-ctrl"
	})
	.when("/order",{
		templateUrl:"/Admin/Orders/index.html",
		controller:"order-ctrl"
	})
	.when("/statistics",{
		templateUrl:"/Admin/thongke/index.html",
		controller:"statistical-ctrl"
	})
	.otherwise({
		template:"Nguyễn Đức Anh",
		controller:"product-ctrl"
	})
})