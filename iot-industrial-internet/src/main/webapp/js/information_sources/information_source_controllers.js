/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

var customNames = [
    {
        realId: "67dbe416-53f2-44b5-bb89-2925f2ae257c",
        customName: "Oh boy"
    }
];


informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', function ($scope, InformationSource) {
        $scope.sources = InformationSource.query();

        $scope.customName = function (id) {
            var ret = id;
            console.log(customNames);
            for (i = 0; i < customNames.length; i++) {
                if (customNames[i].realId === id) {
                    ret = customNames[i].customName;
                    break;
                }
            }


            return ret;
        };
    }]);



informationSources.controller('InformationSourceController', ['$scope', '$routeParams', 'Device', 'InformationSource', 'Sensor',
    function ($scope, $routeParams, Device, InformationSource, Sensor) {

        populateDevicesWithSensors = function () {
            for (var i = 0; i < $scope.devices.length; i++) {
                $scope.devices[i].sensors = Sensor.query({deviceid: $scope.devices[i].id});
            }
        };

        $scope.source = InformationSource.get({informationsourceid: $routeParams.informationsourceid});
        $scope.devices = Device.query({informationsourceid: $routeParams.informationsourceid}, function (value, headers) {
            populateDevicesWithSensors();
        });

    }]);

informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', function ($scope, $routeParams, Sensor, Readout) {
        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});
    }]);

