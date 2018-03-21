var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "views/index.html"
    })
    .when("/blog", {
        templateUrl : "views/blog.html"
    })
    .when("/friend", {
        templateUrl : "views/friend.html"
    })
    .when("/job", {
        templateUrl : "views/job.html"
    })
    .otherwise({
        template : "views/404.html"
    });
});