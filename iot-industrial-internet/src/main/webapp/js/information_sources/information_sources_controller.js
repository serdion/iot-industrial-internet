informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', function ($scope, InformationSource) {
        $scope.sources = InformationSource.query({}, function(value, headers) {
            console.log(value);
        });
    }]);
