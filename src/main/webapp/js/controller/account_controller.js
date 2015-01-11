angular.module("ControllersModule").controller("AccountController", [
    "$scope",
    "$routeParams",
    "$location",
    "SessionService",
    function($scope, $routeParams, $location, SessionService)
    {
        // constructor
        {
            $scope.session = SessionService;
            $scope.action = $routeParams.action;
        };
    }
]);
