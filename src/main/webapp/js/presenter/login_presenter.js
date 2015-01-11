angular.module("ControllersModule").controller("LoginPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "AuthService",
    function($scope, $routeParams, SessionService, AuthService)
    {
        // private
        var login = function()
        {
            AuthService.login(
                $scope.registration,
                $scope.password,
                function(data)
                {
                    SessionService.session = data;

                    $scope.response = "";
                },
                function(data) {
                    $scope.response = data;
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.registration = "";
            $scope.password = "";
            $scope.command = login;
            $scope.response = "";
        };
    }
]);
