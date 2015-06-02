/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

sysErrors.controller('SysErrorsController', function($scope, SysError) {
    $scope.errors = SysError.query(function(data) {
        console.log("Bleeh");
        console.log(data);
    });


});
