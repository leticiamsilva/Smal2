angular.module("ControllersModule").controller("FooterController", [
    "$scope",
    "$location",
    "SessionService",
    function FooterController($scope, $location, SessionService)
    {
        // constructor
        {
            $scope.session = SessionService;
        };
    }
]);
