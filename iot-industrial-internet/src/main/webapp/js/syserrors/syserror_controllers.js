/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

sysErrors.controller('SysErrorsController', function($scope, SysErrorList) {
    $scope.errorlist = SysErrorList.query(function(data) {
        console.log("Get all errors");
        console.log(data);
    });
})

sysErrors.controller('SysErrorsController', function($scope, SysErrorList) {
    $scope.errorlist = SysErrorList.query(function(data) {
        console.log("Get all errors");
        console.log(data);
    });
});
