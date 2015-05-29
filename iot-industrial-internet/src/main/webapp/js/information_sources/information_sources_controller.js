informationSources.controller('InformationSourcesController', ['$scope', 'InformationSource', function ($scope, InformationSource) {
        $scope.list = InformationSource.query({}, function(value, headers) {
            console.log(value);
        });
    }]);
