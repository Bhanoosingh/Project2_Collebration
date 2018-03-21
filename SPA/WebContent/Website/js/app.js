var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "index.html"
    })
    .when("/blog", {
        templateUrl : "blog.html"
    })
    .when("/friend", {
        templateUrl : "friend.html"
    })
    .when("/job", {
        templateUrl : "job.html"
    })
    .otherwise({
        template : "404.html"
    });
});