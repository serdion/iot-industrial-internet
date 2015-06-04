/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


IIFramework.controller('VisualRealtimeController', function ($scope, InformationSource, Readout, $routeParams) {
    var dataToDisplay = [];

    $scope.number = [0, 80];
    $scope.readouts = Readout.query({sensorid: $routeParams.sensorid}, function () {
    $scope.id = $routeParams.sensorid;

        var highestValue = Number.MIN_VALUE;
        var lowestValue = Number.MAX_VALUE;

        for (var i = 0; i < $scope.readouts.length; i++) {
            var time = $scope.readouts[i].time;
            var value = $scope.readouts[i].value;
            
            dataToDisplay[i] = [time, value];
        }
        
        console.log(highestValue +" is bigger than "+ lowestValue);

        $scope.flotData = [{
                data: dataToDisplay,
                label: " " + ($scope.readouts[0].quantity) + " (" + $scope.readouts[0].unit + ")"
            }];
    });

    $scope.change = function () {
        $scope.dataOptions.yaxis.min = $scope.number[0];
        $scope.dataOptions.yaxis.max = $scope.number[1];
    };

    $scope.doit = function () {
        $scope.dataOptions = {
            series: {
                lines: {
                    show: true
                },
                points: {
                    show: true
                }
            },
            grid: {
                hoverable: true // needed for tooltip to work
            },
            yaxis: {
                min: $scope.number[0],
                max: $scope.number[1]
            },
            tooltip: true,
            tooltipOpts: {
                content: "'%s' of %x.1 is %y.4",
                shifts: {
                    x: -60,
                    y: 25
                }
            }
        };
    };

    $scope.dataOptions = {
        series: {
            lines: {
                show: true
            },
            points: {
                show: true
            }
        },
        grid: {
            hoverable: true // needed for tooltip to work
        },
        yaxis: {
            min: $scope.number[0],
            max: $scope.number[1]
        },
        tooltip: true,
        tooltipOpts: {
            content: "'%s' of %x.1 is %y.4",
            shifts: {
                x: -60,
                y: 25
            }
        }
    };

}
);

