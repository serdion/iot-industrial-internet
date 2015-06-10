/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', function($scope, InformationSource) {
        $scope.sources = InformationSource.query();
    }]);


informationSources.controller('InformationSourceController', ['$scope', '$routeParams', 'InformationSource', 'Sensor',
    function ($scope, $routeParams, InformationSource, Sensor) {

        $scope.source = InformationSource.get({informationsourceid: $routeParams.informationsourceid});
        $scope.sensors = Sensor.query({informationsourceid: $routeParams.informationsourceid}, function (value, headers) {
        });

    }]);

informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', function($scope, $routeParams, Sensor, Readout) {
        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});
        $scope.filter = function() {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, more: $scope.more, less: $scope.less});
        }
    }]);

informationSources.controller('DeviceController', ['$scope', '$routeParams', 'Device', 'DeviceConfig', function($scope, $routeParams, Device, DeviceConfig) {
        $scope.device = Device.get({informationsourceid: $routeParams.deviceid});
        $scope.configs = DeviceConfig.query({action : 'list'});
        $scope.config = DeviceConfig.query({deviceid : $routeParams.deviceid, action : 'view'});
    }]);

