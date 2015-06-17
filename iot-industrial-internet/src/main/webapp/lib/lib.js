/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */


function showError(error) {
    var el = angular.element(document.querySelector('#error_container'));
    el.append('<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + error + '</div>');
}

function showSuccess(message) {
    var el = angular.element(document.querySelector('#error_container'));
    el.append('<div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + message + '</div>');
}