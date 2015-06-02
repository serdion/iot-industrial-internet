/*
* IoT - Industrial Internet Framework
* Apache License Version 2.0, January 2004
* Released as a part of Helsinki University
* Software Engineering Lab in summer 2015
*/

configurations.controller('AddInformationSourceController', ['$scope', 'InformationSourceConfiguration', function($scope, InformationSourceConfiguration) {
  $scope.addIS = function() {
    $scope.types = ['XML'];
    var ISConfig = new InformationSourceConfiguration({name: $scope.is_name,
      type: 'XML', url: $scope.is_url, readFrequency: $scope.is_freq});
      ISConfig.$save();
    };
  }]);