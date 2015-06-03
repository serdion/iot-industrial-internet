/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

// Get all syserrors in a list
sysErrors.controller('SysErrorsListController', function($scope, SysErrorList) {
    console.log("Get all errors");
    $scope.errorlist = SysErrorList.query(function(data) {
        console.log(data);
    });
})

//Get one syserro based on id
sysErrors.controller('SysErrorDetailsController', function($scope, SysError, $routeParams) {
    console.log("Get single error!");
    $scope.errordetails = SysError.query({errorid: $routeParams.errorid}, function(data) {
        console.log(data);
    });
});
