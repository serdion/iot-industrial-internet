/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

var informationSources = angular.module('informationSources', ['ngResource']);

informationSources.factory('InformationSource', ['$resource',
    function($resource) {
        return $resource('1.0/sources/:sourceid/:action', {}, {
            get: {method: 'GET', params: {action: 'view'}},
            query: {method: 'GET', params: {action: 'list'}, isArray: true},
            read: {method: 'GET', params: {action: 'read'}},
            edit: {method: 'POST', params: {action: 'edit'}},
            add: {method: 'POST', params: {action: 'add'}},
            delete: {method: 'DELETE', params: {action: 'delete'}}
        });
    }]);


informationSources.factory('Sensor', ['$resource',
    function($resource) {
        return $resource('1.0/sensors/:sourceid/:sensorid/:action', {}, {
            get: {method: 'GET', params: {action: 'view'}},
            query: {method: 'GET', params: {action: 'list'}, isArray: true},
            edit: {method: 'POST', params: {action: 'edit'}}
        });
    }]);


informationSources.factory('Readout', ['$resource',
    function($resource) {
        return $resource('1.0/readouts/:sensorid/:action/:from/:to', {}, {
            query: {method: 'GET', params: {action: 'list'}, isArray: true}
        });
    }]);

informationSources.factory('InformationSourceType', ['$resource',
    function($resource) {
        return $resource('1.0/stats/informationsource/types', {}, {
            query: {method: 'GET', isArray: true}
        });
    }]);
