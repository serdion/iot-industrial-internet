/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


IIFramework.controller('VisualRealtimeController', function ($scope, InformationSource) {

    $scope.sensor = Sensor.get({sensorid: $routeParams.sensorid});
    $scope.readouts = Readout.query({sensorid: $routeParams.sensorid});

    var dataToDisplay = [];

    $scope.data = [{
            data: dataToDisplay,
            label: "NaN"
        }];

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
            min: -1.2,
            max: 1.2
        },
        tooltip: true,
        tooltipOpts: {
            content: "'%s' of %x.1 is %y.4",
            shifts: {
                x: -60,
                y: 25
            }
        }
    }

    $scope.sincosData = [{
            data: sin,
            label: "sin(x)"
        }, {
            data: cos,
            label: "cos(x)"
        }];

    $scope.sincosOptions = {
        series: {
            lines: {
                show: true
            },
            points: {
                show: true
            }
        },
        grid: {
            hoverable: true //IMPORTANT! this is needed for tooltip to work
        },
        yaxis: {
            min: -1.2,
            max: 1.2
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

    $scope.oilData = [{
            data: oilprices,
            label: "Oil price ($)"
        }, {
            data: exchangerates,
            label: "USD/EUR exchange rate",
            yaxis: 2
        }];
    $scope.oilOptions = {
        xaxes: [{
                mode: 'time'
            }],
        yaxes: [{
                min: 0
            }, {
                // align if we are to the right
                alignTicksWithAxis: 1,
                position: "right",
                tickFormatter: euroFormatter
            }],
        legend: {
            position: 'sw'
        },
        grid: {
            hoverable: true //IMPORTANT! this is needed for tooltip to work
        },
        tooltip: true,
        tooltipOpts: {
            content: "%s for %x was %y",
            xDateFormat: "%y-%0m-%0d",
            onHover: function (flotItem, $tooltipEl) {
                // console.log(flotItem, $tooltipEl);
            }
        }

    };
});
