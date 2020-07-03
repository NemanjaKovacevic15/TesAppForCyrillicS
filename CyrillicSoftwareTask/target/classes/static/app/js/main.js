var testApp = angular.module("testApp", ["ngRoute"]);

testApp.controller("FarmsCtrl", function($scope, $http){

    var url = "/api/farms";

    $scope.farms = [];

    var getFarms = function(){
        var promise = $http.get(url);
        promise.then(
            function success(res){
                $scope.farms = res.data;
            },
            function error(){
                alert("No farms found.");
            }
        );
    };

    getFarms();
});

testApp.controller("AccountsCtrl", function($scope, $http){

    var url = "/api/accounts";

    $scope.accounts = [];

    var getAccounts = function(){
        var promise = $http.get(url);
        promise.then(
            function success(res){
                $scope.accounts = res.data;
            },
            function error(){
                alert("No accounts found.");
            }
        );
    };

    getAccounts();

});

testApp.controller("UsersCtrl", function($scope, $http){

    var url = "/api/users";

    $scope.users = [];

    var getUsers = function(){
        var promise = $http.get(url);
        promise.then(
            function success(res){
                $scope.users = res.data;
            },
            function error(){
                alert("No users found.");
            }
        );
    };

    getUsers();

});

testApp.controller("CustomersCtrl", function($scope, $http){

    var url = "/api/customers";

    $scope.customers = [];

    var getCustomers = function(){
        var promise = $http.get(url);
        promise.then(
            function success(res){
                $scope.customers = res.data;
            },
            function error(){
                alert("No customers found.");
            }
        );
    };

    getCustomers();

});

testApp.controller("UserPageCtrl", function($scope, $http){

    var url = "/api/users/auth";
    $scope.user = {};
    var currentId = 1;

    var getLoggedUser = function(){
        var promise = $http.get(url);
        promise.then(
            function success(res){
                $scope.user = res.data;
                currentId = $scope.user.id;

                getUserAccounts();
                getUserFarms();
            },
            function error(){
            }
        );
    };

    getLoggedUser();

    $scope.accounts = [];

    var getUserAccounts = function(){
        var promise = $http.get("/api/users/"+  currentId +"/accounts");
        promise.then(
            function success(res){
                $scope.accounts = res.data;
            },
            function error(){
            }
        );
    };


    $scope.farms = [];

    var getUserFarms = function(){
        var promise = $http.get("/api/users/"+  currentId +"/farms");
        promise.then(
            function success(res){
                $scope.farms = res.data;
            },
            function error(){
            }
        );
    };

});

testApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/userPage.html'
        })
        .when('/farms', {
			templateUrl : '/app/html/farms.html'
        })
        .when('/accounts', {
			templateUrl : '/app/html/accounts.html'
        })
        .when('/users', {
			templateUrl : '/app/html/users.html'
        })
        .when('/customers', {
			templateUrl : '/app/html/customers.html'
        })
		.otherwise({
			redirectTo: '/'
		});
}]);