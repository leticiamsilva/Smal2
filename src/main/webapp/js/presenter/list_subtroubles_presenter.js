angular.module("ControllersModule").controller("ListSubTroublesPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "SubTroubleService",
    function($scope, $routeParams, SessionService, SubTroubleService)
    {
        // private
        var listSubTroubles = function()
        {
            SubTroubleService.getSubTroubles(
                SessionService.session_id,
                $scope.trouble_name,
                function(data)
                {
                    $scope.subtroubles = data;
                },
                function(data) {
                    $scope.response = data;
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.trouble_name = $routeParams.trouble_name;
            $scope.command = listSubTroubles;
            $scope.subtroubles = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
