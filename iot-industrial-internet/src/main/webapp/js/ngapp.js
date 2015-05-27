/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


var IIFramework = angular.module('IIFramework', ['ngRoute']);


IIFramework.config(function($routeProvider){
    console.log($routeProvider);
    $routeProvider.when('/', {
        controller: 'FrontController',
        templateUrl: 'ngviews/front.html'
    });
});