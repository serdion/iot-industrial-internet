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
                text: "Your are about to delete a source with id " + id + ".",
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
                    SweetAlert.swal("Deleted!", "A source with id " + id + " has been deleted.", "success");
                } else {
                    SweetAlert.swal("Cancelled!", "Delete action has been cancelled.", "error");
                }
            });
        };

        $scope.readSource = function (id) {
            InformationSource.read({sourceid: id}, function (success) {
                console.log(success);
                showSuccess(success.message);
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

        $scope.toggleSensorInView = function (action, sensor) {
            if (action == "on") {
                sensor.active = true;
            }
            else {
                sensor.active = false;
            }
            sensor.$edit({sensorid: sensor.id}, function () {
            });
        };
    }]);


informationSources.controller('SensorController', ['$scope', '$routeParams', 'Sensor', 'Readout', '$window', function ($scope, $routeParams, Sensor, Readout, $window) {

        $scope.currentPage = 1;
        $scope.itemsPerPage = 25;

        $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
        $scope.sensorStats = Sensor.stats({sensorid: $routeParams.sensorid});
        $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, from: $scope.currentPage * $scope.itemsPerPage - $scope.itemsPerPage, to: $scope.currentPage * $scope.itemsPerPage});

        $scope.pageChanged = function () {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, from: $scope.currentPage * $scope.itemsPerPage - $scope.itemsPerPage, to: $scope.currentPage * $scope.itemsPerPage});
        }

        //Function to allow reading of sensor.active value into the UI properly
        $scope.boolToStr = function (arg) {
            return arg ? 'true' : 'false';
        };

        $scope.filter = function () {
            $scope.readouts = Readout.query({sensorid: $routeParams.sensorid, more: $scope.more, less: $scope.less});
        };

        $scope.save = function () {
            $scope.sensor.$edit({sensorid: $routeParams.sensorid}, function () {
                $window.history.back();

            });
        };
    }]);

informationSources.controller('AddInformationSourceController', ['$scope', 'InformationSource', '$location', function ($scope, InformationSource, $location) {
        $scope.header = "Add Source";
        $scope.is = new InformationSource();
        $scope.startDate = new Date();
        $scope.is.readInterval = 'NEVER';

        $scope.submit = function () {
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
            $scope.is.$add({}, function () {
                $location.path('/sources');
            },
                    function (error) {
                        showError(error.data.message);
                    });
        };
    }]);

informationSources.controller('EditInformationSourceController', ['$scope', 'InformationSource', '$location', '$routeParams', function ($scope, InformationSource, $location, $routeParams) {
        $scope.is = InformationSource.get({sourceid: $routeParams.sourceid}, function () {
            $scope.startDate = new Date($scope.is.startDate);
            $scope.endDate = new Date($scope.is.endDate);
            $scope.header = "Edit source " + $scope.is.name;
        });


        $scope.submit = function () {
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
            $scope.is.$edit({}, function () {
                $location.path('/sources');
            },
                    function (error) {
                        showError(error.data.message);
                    });
        };
    }]);

informationSources.controller('InformationSourceFormController', ['$scope', 'InformationSource', 'InformationSourceType', '$location', '$routeParams', function ($scope, InformationSource, InformationSourceType, $location, $routeParams) {
        $scope.types = InformationSourceType.query();

        $scope.back = function () {
            window.history.back();
        };


        $scope.minDate = $scope.minDate ? null : new Date();

        $scope.toggleStartDatePicker = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.startDatePickerOpened = !$scope.startDatePickerOpened;
        };

        $scope.toggleEndDatePicker = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.endDatePickerOpened = !$scope.endDatePickerOpened;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };
    }]);
