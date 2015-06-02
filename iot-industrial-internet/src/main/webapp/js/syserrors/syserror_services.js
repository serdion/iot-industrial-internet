/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


var sysErrors = angular.module('sysErrors', ['ngResource']);

sysErrors.factory('SysError', ['$resource',
    function($resource) {
        return $resource('1.0/datasources/:datasourceid/:action', {}, {
            get: {method: 'GET', params: {action: 'view'}},
            query: {method: 'GET', params: {datasourceid: 'list'}, isArray: true}
        });
    }]);