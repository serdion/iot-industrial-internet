/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

sysErrors.controller('SysErrorsController', ['$scope', 'SysError', function ($scope, SysError) {
        $scope.sources = SysError.query({}, function (value, headers) {
            console.log(value);
        });
    }]);
