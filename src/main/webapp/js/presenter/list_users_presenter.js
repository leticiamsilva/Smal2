angular.module("ControllersModule").controller("ListUsersPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "UserService",
    function($scope, $routeParams, SessionService, UserService)
    {
        // private
        var listUsers = function()
        {
            UserService.getUsers(
                SessionService.session_id,
                function(data)
                {
                    $scope.users = data;
                },
                function(data) {
                    $scope.response = data;
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.command = listUsers;
            $scope.users = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
