/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


/* global informationSources */

informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', function ($scope, InformationSource) {
        $scope.sources = InformationSource.query();

        $scope.deleteSource = function (id) {
            InformationSource.delete({sourceid: id}, function () {
                $scope.sources = InformationSource.query();
            }, function (error) {
                showError(error.data.message);
            });
        };

    }]);


informationSources.controller('InformationSourceController', ['$scope', '$routeParams', 'InformationSource', 'Sensor',
    function ($scope, $routeParams, InformationSource, Sensor) {
        $scope.source = InformationSource.get({sourceid: $routeParams.sourceid});
        $scope.sensors = Sensor.query({sourceid: $routeParams.sourceid}, function (value, headers) {
        });

    }]);

informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', 'SensorConfiguration', function ($scope, $routeParams, Sensor, Readout, SensorConfiguration) {
        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});

        $scope.filter = function () {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, more: $scope.more, less: $scope.less});
        };

        $scope.sensorconf = SensorConfiguration.get({sensorid: $routeParams.sensorid});

        $scope.save = function () {

            $scope.configuration = new SensorConfiguration();

            $scope.configuration.quantity = $scope.newconfig.quantity;
            $scope.configuration.unit = $scope.newconfig.unit;

            $scope.configuration.thresholdMin = $scope.newconfig.thresholdMin;
            $scope.configuration.thresholdMax = $scope.newconfig.thresholdMax;
            $scope.configuration.active = $scope.newconfig.active;

            $scope.configuration.$add({sensorid: $routeParams.sensorid}, function () {
                $scope.sensorconf = SensorConfiguration.get({sensorid: $routeParams.sensorid});
            });

        };

        $scope.refresh = function () {
            $scope.sensorconf = SensorConfiguration.get({sensorid: $routeParams.sensorid});
        };


    }]);

informationSources.controller('AddInformationSourceController', ['$scope', 'InformationSource', '$location', function ($scope, InformationSource, $location) {
        $scope.types = ['XML', 'JSON'];

        $scope.is = new InformationSource();

        $scope.back = function () {
            window.history.back();
        };

        $scope.submit = function () {
            $scope.is.readFrequency = $scope.readFrequency_s * 1000;
            $scope.is.readInterval = $scope.radioModel;
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
//            $scope.is.startDate = $scope.startDate + $scope.time;
            $scope.is.$save({}, function () {
                $location.path('/sources');
            },
                    function (error) {
                        showError(error.data.message);
                    });
        };

        $scope.today = function () {
            $scope.startDate = new Date();
            $scope.endDate = new Date();
        };
        $scope.today();

        $scope.toggleMin = function () {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.radioModel = 'Never';
    }]);

informationSources.controller('EditInformationSourceController', ['$scope', 'InformationSource', '$location', '$routeParams', function ($scope, InformationSource, $location, $routeParams) {
        $scope.types = ['XML', 'JSON'];

        $scope.is = InformationSource.get({sourceid: $routeParams.sourceid}, function () {
            $scope.readFrequency_s = $scope.is.readFrequency / 1000;
            $scope.startDate = $scope.is.startDate;
            $scope.endDate = $scope.is.endDate;
            $scope.radioModel = $scope.is.readInterval;
        });

        $scope.back = function () {
            window.history.back();
        };
        $scope.submit = function () {
            $scope.is.readFrequency = $scope.readFrequency_s * 1000;
            $scope.is.readInterval = $scope.radioModel;
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
//            $scope.is.startDate = $scope.startDate + $scope.time;
            $scope.is.$edit({}, function () {
                $location.path('/sources');
            },
                    function (error) {
                        showError(error.data.message);
                    });
        };

        $scope.toggleMin = function () {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };
    }]);

