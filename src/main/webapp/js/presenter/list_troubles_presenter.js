angular.module("ControllersModule").controller("ListTroublesPresenter", [
    "$scope",
    "SessionService",
    "TroubleService",
    function($scope, SessionService, TroubleService)
    {
        // private
        var listTroubles = function()
        {
            TroubleService.getTroubles(
                SessionService.session_id,
                function(data)
                {
                    $scope.troubles = data;
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
