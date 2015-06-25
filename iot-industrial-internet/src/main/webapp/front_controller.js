/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


IIFramework.controller('FrontController', function ($scope, InformationSource, Sensor, Readout){
    $scope.numberOfSources = InformationSource.count();
    $scope.numberOfSensors = Sensor.count();
    $scope.numberOfReadouts = Readout.count();
});