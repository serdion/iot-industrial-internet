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

        $scope.readSource = function (id) {
            InformationSource.read({sourceid: id}, function () {
                showSuccess("Read performed");
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


informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', '$window', function($scope, $routeParams, Sensor, Readout, $window) {

        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});


        //Function to allow reading of sensor.active value into the UI properly
        $scope.boolToStr = function (arg) {
            return arg ? 'true' : 'false';
        };

        $scope.filter = function () {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, more: $scope.more, less: $scope.less});
        };


        $scope.save = function () {
//            console.log($scope.sensor);
            $scope.sensor.$edit({sensorid: $routeParams.sensorid}, function() {
                $window.history.back();

            });

        };



    }]);

informationSources.controller('AddInformationSourceController', ['$scope', 'InformationSource', '$location', function ($scope, InformationSource, $location) {
        $scope.types = ['XML', 'JSON'];

        $scope.is = new InformationSource();

        $scope.back = function () {
            window.history.back();
        };

        $scope.submit = function () {
            $scope.is.otherInterval = $scope.otherInterval * 1000;
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

        $scope.radioModel = 'NEVER';
    }]);

informationSources.controller('EditInformationSourceController', ['$scope', 'InformationSource', '$location', '$routeParams', function ($scope, InformationSource, $location, $routeParams) {
        $scope.types = ['XML', 'JSON'];

        $scope.is = InformationSource.get({sourceid: $routeParams.sourceid}, function () {
            $scope.otherInterval = $scope.is.otherInterval / 1000;
            $scope.startDate = $scope.is.startDate;
            $scope.endDate = $scope.is.endDate;
            $scope.radioModel = $scope.is.readInterval;
            $scope.startTime = $scope.is.startTime;
        });

        $scope.back = function () {
            window.history.back();
        };
        $scope.submit = function () {
            $scope.is.otherInterval = $scope.otherInterval * 1000;
            $scope.is.readInterval = $scope.radioModel;
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
//            $scope.is.startDate = $scope.startDate + 5000000;
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

