angular.module("ControllersModule").controller("RegisterSubTroublePresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "SubTroubleService",
    function($scope, $routeParams, SessionService, SubTroubleService)
    {
        // private
        var registerSubTrouble = function()
        {
            SubTroubleService.registerSubTrouble(
                SessionService.session_id,
                $scope.name,
                $scope.trouble_name,
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
            $scope.name = "";
            $scope.command = registerSubTrouble;
            $scope.response = "";
        };
    }
]);
