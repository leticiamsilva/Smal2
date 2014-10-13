angular.module("ControllersModule").controller("RegisterUserPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "UserService",
    function($scope, $routeParams, SessionService, UserService)
    {
        // private
        var registerUser = function()
        {
            UserService.registerUser(
                SessionService.session_id,
                $scope.registration,
                $scope.password,
                $scope.name,
                Util.parseDate($scope.birth_date),
                $scope.type,
                function(data)
                {
                    $scope.response = data;
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
            $scope.name = "";
            $scope.birth_date = "";
            $scope.type = "";
            $scope.command = registerUser;
            $scope.response = "";
        };
    }
]);
