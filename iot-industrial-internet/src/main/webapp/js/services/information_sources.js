var IIFrameworkServices = angular.module('IIFramework', ['ngResource']);

IIFrameworkServices.factory('InformationSource' ['$resource', 
	function($resource){
		return('1.0/datasources/list')
	}]);
