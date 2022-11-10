app.controller("statistical-ctrl", function($scope, $http){
   
    $scope.date = new Date();
    $scope.items = [];
    $scope.count = "";
    $scope.amount = "";
    $scope.load = function(){
        var day = $scope.date;
        var count ="";
        //load items // ds sp ban trong ngay
        $http.get(`/rest/statistical/${day}`).then(resp =>{
            $scope.items = resp.data;
        
            $scope.count = $scope.items.map(sp => sp[2])
                    .reduce((total, qty) => total += qty,0);

            $scope.amount = $scope.items.map(sp => sp[2]*sp[1])
                    .reduce((total, pri) => total += pri,0);
            
        })
        
    }

    
    $scope.load();

});