angular.module("ControllersModule").controller("ListTroublesPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "TroubleService",
    function($scope, $routeParams, SessionService, TroubleService)
    {
        // private
        var listTroubles = function()
        {
            TroubleService.getTroubles(
                SessionService.session.session_id,
                function(data)
                {
                    $scope.troubles = data;

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
            $scope.command = listTroubles;
            $scope.troubles = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
