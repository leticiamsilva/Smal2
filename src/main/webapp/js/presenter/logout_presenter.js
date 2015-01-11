angular.module("ControllersModule").controller("LogoutPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "AuthService",
    function($scope, $routeParams, SessionService, AuthService)
    {
        // private
        var logout = function()
        {
            AuthService.logout(
                SessionService.session.session_id,
                function(data)
                {
                    $scope.response = data;
                    SessionService.session = null;
                },
                function(data) {
                    $scope.response = data;
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.command = logout;
            $scope.response = "";
        };
    }
]);
