/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


/* global informationSources */

informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', function($scope, InformationSource) {
        $scope.sources = InformationSource.query();
    }]);


informationSources.controller('InformationSourceController', ['$scope', '$routeParams', 'InformationSource', 'Sensor',
    function($scope, $routeParams, InformationSource, Sensor) {

        $scope.source = InformationSource.get({sourceid: $routeParams.sourceid});
        $scope.sensors = Sensor.query({sourceid: $routeParams.sourceid}, function(value, headers) {
        });

    }]);

informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', 'SensorConfiguration', function($scope, $routeParams, Sensor, Readout, SensorConfiguration) {
        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});

        $scope.filter = function() {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, more: $scope.more, less: $scope.less});
        };


        $scope.sensorconf = SensorConfiguration.get({sensorid: $routeParams.sensorid});
//        $scope.sensorconflist = SensorConfiguration.query();




        $scope.save = function() {

            $scope.configuration = new SensorConfiguration();

            $scope.configuration.quantity = $scope.newconfig.quantity;
            $scope.configuration.unit = $scope.newconfig.unit;

            $scope.configuration.thresholdMin = $scope.newconfig.thresholdMin;
            $scope.configuration.thresholdMax = $scope.newconfig.thresholdMax;

            $scope.configuration.$add({sensorid: $routeParams.sensorid}, function() {
                $scope.sensorconf = SensorConfiguration.get({sensorid: $routeParams.sensorid});
            });

        };

        $scope.refresh = function() {
            $scope.sensorconf = SensorConfiguration.get({sensorid: $routeParams.sensorid});
        };


    }]);



