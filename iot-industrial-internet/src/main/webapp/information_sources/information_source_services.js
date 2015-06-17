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
            edit: {method: 'POST', params: {action: 'edit'}},
            save: {method: 'POST', params: {action: 'add'}},
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
        return $resource('1.0/readouts/:sensorid/:action', {}, {
            query: {method: 'GET', params: {action: 'list'}, isArray: true}
        });
    }]);
