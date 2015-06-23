/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

// Get all syserrors in a list
sysErrors.controller('SysErrorsListController', function ($scope, SysError, $routeParams) {
    $scope.errorlist = SysError.get(function () {
    });

    $scope.setstatus = function (error, stat) {
        error.$setstatus({errorid: error.id, viewed: stat});
    }
});

//Get one syserror based on id
sysErrors.controller('SysErrorDetailsController', function ($scope, SysError, $routeParams) {
    $scope.errordetails = SysError.query({errorid: $routeParams.errorid}, function (data) {
//        console.log(data);
    });
    $scope.back = function () {
        window.history.back();
    };
});

sysErrors.controller('ErrorNotificationController', function ($scope, SysError, $routeParams) {
    $scope.alarmlist = SysError.get({errorid: $routeParams.errorid}, function (data) {
    });
    $scope.reloadErrors = function () {
        $scope.alarmlist = SysError.get();
    };
});
