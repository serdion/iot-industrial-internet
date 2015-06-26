/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

var checkForNewAlarms = function (alarmlist) {
    for (var i = 0; i < alarmlist.length; i++) {
        if (alarmlist[i].viewed == false) {
            return true;
        }
    }
    return false;
}

// Get all syserrors in a list
sysErrors.controller('SysErrorsListController', function ($scope, SysError, $routeParams) {
    $scope.statusswitch = false;

    $scope.currentPage = 1;
    $scope.itemsPerPage = 25;
    
    $scope.numberOfErrors = SysError.count()

    $scope.getErrors = function () {
        $scope.errorlist = SysError.query({from: ($scope.currentPage - 1) * $scope.itemsPerPage, to: $scope.currentPage * $scope.itemsPerPage - 1});
    };
    $scope.getErrors();

    $scope.pageChanged = function () {
        $scope.getErrors();
    };

    $scope.statuschangetext = function () {
        if ($scope.statusswitch) {
            return "Mark all un-read";
        } else {
            return "Mark all read";
        }
    };

    $scope.setstatusall = function (status) {
        $scope.statusswitch = status;
        for (var i = 0; i < $scope.errorlist.length; i++) {
            $scope.setstatus($scope.errorlist[i], status);
        }
    };

    $scope.setstatus = function (error, status) {
        error.$setstatus({errorid: error.id, setviewed: status});
    };
});

//Get one syserror based on id
sysErrors.controller('SysErrorDetailsController', function ($scope, SysError, $routeParams) {
    $scope.errordetails = SysError.get({errorid: $routeParams.errorid}, function (data) {
        data.$setstatus({errorid: data.id, setviewed: true});
    });
    $scope.back = function () {
        window.history.back();
    };
});

sysErrors.controller('ErrorNotificationController', function ($scope, SysError, $routeParams) {
    $scope.alarmlist = SysError.query({from: 0, to: 5, viewed: false}, function () {
        $scope.unviewedErrors = checkForNewAlarms($scope.alarmlist);
    });

    $scope.reloadErrors = function () {
        $scope.alarmlist = SysError.query({from: 0, to: 5, viewed: false}, function () {
            $scope.unviewedErrors = checkForNewAlarms($scope.alarmlist);
        });
    };

    // Get new errors every 10 second
    setInterval(function () {
        $scope.reloadErrors()
    }, 10000);
});
