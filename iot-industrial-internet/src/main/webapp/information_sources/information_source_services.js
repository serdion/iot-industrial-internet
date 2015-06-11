/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

var informationSources = angular.module('informationSources', ['ngResource']);

informationSources.factory('InformationSource', ['$resource',
    function($resource) {
        return $resource('1.0/informationsources/:informationsourceid/:action', {}, {
            get: {method: 'GET', params: {action: 'view'}},
            query: {method: 'GET', params: {informationsourceid: 'list'}, isArray: true}
        });
    }]);


informationSources.factory('Sensor', ['$resource',
    function($resource) {
        return $resource('1.0/sensors/:informationsourceid/:sensorid/:action', {}, {
            get: {method: 'GET', params: {action: 'view'}},
            query: {method: 'GET', params: {action: 'list'}, isArray: true}
        });
    }]);

informationSources.factory('SensorConfig', ['$resource',
    function($resource) {
        return $resource('1.0/configurations/sensors/:sensorid/:action', {}, {
            get: {method: 'GET', params: {action: 'view'}},
            query: {method: 'GET', params: {action: 'list'}, isArray: true},
            add: {method: 'GET', params: {action: 'list'}, isArray: true}
        });
    }]);


informationSources.factory('Readout', ['$resource',
    function($resource) {
        return $resource('1.0/readouts/:sensorid/:action', {}, {
            query: {method: 'GET', params: {action: 'list'}, isArray: true}
        });
    }]);
