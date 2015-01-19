angular.module("ControllersModule").controller("ListPrivilegedUsersPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "UserService",
    function($scope, $routeParams, SessionService, UserService)
    {
        // private
        var listPrivilegedUsers = function()
        {
            UserService.getPrivilegedUsers(
                SessionService.session.session_id,
                function(data)
                {
                    $scope.users = data;

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
            $scope.command = listPrivilegedUsers;
            $scope.users = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
