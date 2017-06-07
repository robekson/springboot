
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
});



app.controller('myCtrl', function($scope, $http) {

    $http.get('/resource/').success(function(data) {
       $scope.greeting = data;
    });
});

