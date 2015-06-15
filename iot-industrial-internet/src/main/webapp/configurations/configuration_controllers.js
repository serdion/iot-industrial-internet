/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

function showError(error) {
    var el = angular.element(document.querySelector('#error_container'));
    el.append('<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + error + '</div>');
}

function comicMe() {
    $('body').css("font-family", "Comic Sans MS, cursive, sans-serif");
}

configurations.controller('AddInformationSourceController', ['$scope', 'InformationSourceConfiguration', '$location', function ($scope, InformationSourceConfiguration, $location) {
        $scope.types = ['XML'];

        $scope.is = new InformationSourceConfiguration();

        $scope.datepickURL = "#/configurations/add#datepick";
        $scope.frequencypickURL = "#/configurations/add#frequencypick";

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
                $location.path('/configurations');
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

configurations.controller('EditInformationSourceController', ['$scope', 'InformationSourceConfiguration', '$location', '$routeParams', function ($scope, InformationSourceConfiguration, $location, $routeParams) {
        $scope.types = ['XML'];

        $scope.is = InformationSourceConfiguration.get({configid: $routeParams.configid}, function () {
            $scope.readFrequency_s = $scope.is.readFrequency / 1000;
            $scope.startDate = $scope.is.startDate;
            $scope.endDate = $scope.is.endDate;
            $scope.radioModel = $scope.is.readInterval;
        });

        $scope.back = function () {
            window.history.back();
        };

        $scope.datepickURL = "#/configurations/{{is.id}}/edit#datepick";
        $scope.frequencypickURL = "#/configurations/{{is.id}}/edit#frequencypick";

        $scope.submit = function () {
            $scope.is.readFrequency = $scope.readFrequency_s * 1000;
            $scope.is.readInterval = $scope.radioModel;
            $scope.is.startDate = $scope.startDate;
            $scope.is.endDate = $scope.endDate;
//            $scope.is.startDate = $scope.startDate + $scope.time;
            $scope.is.$edit({}, function () {
                $location.path('/configurations');
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

configurations.controller('InformationSourceConfigurationsController', ['$scope', 'InformationSourceConfiguration', '$location', function ($scope, InformationSourceConfiguration, $location) {
        $scope.configurations = InformationSourceConfiguration.query();

        $scope.deleteConfiguration = function (id) {
            InformationSourceConfiguration.delete({configid: id}, function () {
                $scope.configurations = InformationSourceConfiguration.query();
            }, function (error) {
                showError(error.data.message);
            });
        };
    }]);