var informationSources = angular.module('informationSources', ['ngResource']);

informationSources.factory('InformationSource', ['$resource',
        function ($resource) {
            return $resource('1.0/datasources/:id/:amount', {amount: 1}, {
                query: {method: 'GET', params: {id: 'list'}, isArray: true}
            });
        }]);

