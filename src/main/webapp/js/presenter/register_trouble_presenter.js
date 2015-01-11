angular.module("ControllersModule").controller("RegisterTroublePresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "TroubleService",
    function($scope, $routeParams, SessionService, TroubleService)
    {
        // private
        var registerTrouble = function()
        {
            TroubleService.registerTrouble(
                SessionService.session.session_id,
                $scope.name,
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
            $scope.command = registerTrouble;
            $scope.response = "";
        };
    }
]);
