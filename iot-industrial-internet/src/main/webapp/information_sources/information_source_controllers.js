/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


/* global informationSources */


informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', 'SweetAlert', function ($scope, InformationSource, SweetAlert) {
        $scope.sources = InformationSource.query();

        $scope.deleteSource = function (id) {
            console.log("Pressed");
            SweetAlert.swal({
                title: "Are you sure?",
                text: "Your are about to delete a source with id "+ id + ".",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55", confirmButtonText: "Delete",
                cancelButtonText: "Cancel",
                closeOnConfirm: false,
                closeOnCancel: false},
            function (isConfirm) {
                if (isConfirm) {
                    console.log("Confirmed");
                    InformationSource.delete({sourceid: id}, function () {
                        $scope.sources = InformationSource.query();
                    }, function (error) {
                        showError(error.data.message);
                    });
                    SweetAlert.swal("Deleted!", "A source with id + "+ id +" has been deleted.", "success");
                } else {
                    SweetAlert.swal("Cancelled!", "Delete action has been cancelled.", "error");
                }
            });
        };

        $scope.readSource = function(id) {
            InformationSource.read({sourceid: id}, function(success) {
                console.log(success);
                showSuccess(success.message);
            }, function(error) {
                showError(error.data.message);
            });
        };
    }]);


informationSources.controller('InformationSourceController', ['$scope', '$routeParams', 'InformationSource', 'Sensor',

    function ($scope, $routeParams, InformationSource, Sensor) {
        $scope.source = InformationSource.get({sourceid: $routeParams.sourceid});
        $scope.sensors = Sensor.query({sourceid: $routeParams.sourceid}, function(value, headers) {
        });

        $scope.toggleSensorInView = function(action, sensor) {
            console.log(action + " " + sensor);
            if (action == "on") {
                sensor.active = true;
            }
            else {
                sensor.active = false;
            }
            sensor.$edit({sensorid: sensor.id}, function() {
            });
        };

    }]);


informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', '$window', function($scope, $routeParams, Sensor, Readout, $window) {

        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});

        //Function to allow reading of sensor.active value into the UI properly
        $scope.boolToStr = function(arg) {
            return arg ? 'true' : 'false';
        };

        $scope.filter = function() {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, more: $scope.more, less: $scope.less});
        };

        $scope.save = function() {
            $scope.sensor.$edit({sensorid: $routeParams.sensorid}, function() {
                $window.history.back();

            });
        };
    }]);


informationSources.controller('AddInformationSourceController', ['$scope', 'InformationSource', '$location', function ($scope, InformationSource, $location) {
        $scope.types = ['XML', 'JSON'];

        $scope.is = new InformationSource();

        $scope.header = "Create a New Source";

        $scope.back = function() {
            window.history.back();
        };

        $scope.submit = function() {
            $scope.is.otherInterval = $scope.otherInterval;
            $scope.is.readInterval = $scope.radioModel;
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
            $scope.is.$save({}, function () {
                $location.path('/sources');
            },
                    function(error) {
                        showError(error.data.message);
                    });
        };

        $scope.startDate = new Date();

        $scope.minDate = $scope.minDate ? null : new Date();

        $scope.open = function($event) {
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

        $scope.header = "Edit a Source";

        $scope.is = InformationSource.get({sourceid: $routeParams.sourceid}, function() {
            $scope.otherInterval = $scope.is.otherInterval;
            $scope.startDate = $scope.is.startDate;
            $scope.radioModel = $scope.is.readInterval;
            $scope.endDate = $scope.is.endDate;
        });

        $scope.back = function() {
            window.history.back();
        };
        $scope.submit = function() {
            $scope.is.otherInterval = $scope.otherInterval;
            $scope.is.readInterval = $scope.radioModel;
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
            $scope.is.$edit({}, function() {
                $location.path('/sources');
            },
                    function(error) {
                        showError(error.data.message);
                    });
        };

        $scope.minDate = $scope.minDate ? null : new Date();

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };
    }]);

