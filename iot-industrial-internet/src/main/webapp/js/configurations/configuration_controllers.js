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

configurations.controller('AddInformationSourceController', ['$scope', 'InformationSourceConfiguration', '$location', function ($scope, InformationSourceConfiguration, $location) {
        $scope.types = ['XML'];
        
        $scope.back = function () {
            window.history.back();
        };
        
        $scope.addIS = function () {
            var ISConfig = new InformationSourceConfiguration({name: $scope.is_name,
                type: $scope.is_type, url: $scope.is_url, readFrequency: $scope.is_freq});
            ISConfig.$save({}, function () {
                $location.path('/configurations');
            },
                    function (error) {
                        showError(error.data.message);
                    });
        };
    }]);

configurations.controller('InformationSourceConfigurationsController', ['$scope', 'InformationSourceConfiguration', '$location', function ($scope, InformationSourceConfiguration, $location) {
        $scope.configurations = InformationSourceConfiguration.query();

    $scope.deleteConfiguration = function (id) {
      InformationSourceConfiguration.delete({configid: id});
      $scope.configurations = InformationSourceConfiguration.query();
    };
  }]);
