/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


var IIFramework = angular.module('IIFramework', [
    'ngRoute',
    'angular-flot',
    'informationSources',
    'sysErrors',
    'angularMoment',
    'ui.bootstrap'
]);


IIFramework.config(function ($routeProvider) {
    $routeProvider.when('/', {
        controller: 'FrontController',
        templateUrl: 'front.html'
    })
            .when('/visual/now/:sensorid', {
                controller: 'VisualRealtimeController',
                templateUrl: 'information_sources/line_graph.html'
            })
            .when('/sources', {
                controller: 'InformationSourcesController',
                templateUrl: 'information_sources/list.html'
            })
            .when('/sources/add', {
                controller: 'AddInformationSourceController',
                templateUrl: 'information_sources/information_source_form.html'
            })
            .when('/sources/:sourceid', {
                controller: 'InformationSourceController',
                templateUrl: 'information_sources/view.html'
            })
            .when('/sources/:sourceid/edit', {
                controller: 'EditInformationSourceController',
                templateUrl: 'information_sources/information_source_form.html'
            })
            .when('/sensors/:sensorid', {
                controller: 'SensorController',
                templateUrl: 'sensors/sensor.html'
            })
            .when('/sensors/:sensorid/edit', {
                controller: 'SensorController',
                templateUrl: 'sensors/edit.html'
            })
            // Controllers for SysErrors
            .when('/syserrors/:errorid', {
                controller: 'SysErrorDetailsController',
                templateUrl: 'syserrors/view.html'
            })
            .when('/syserrors/', {
                controller: 'SysErrorsListController',
                templateUrl: 'syserrors/list.html'
            });
});
