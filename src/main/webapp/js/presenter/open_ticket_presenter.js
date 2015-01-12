angular.module("ControllersModule").controller("OpenTicketPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "TicketService",
    function($scope, $routeParams, SessionService, TicketService)
    {
        // private
        var openTicket = function()
        {
            TicketService.openTicket(
                SessionService.session.session_id,
                $scope.asset_code,
                $scope.trouble,
                $scope.sub_trouble,
                $scope.description,
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
            $scope.asset_code = $routeParams.computer_asset_code;
            $scope.trouble = "";
            $scope.sub_trouble = "";
            $scope.description = "";
            $scope.command = openTicket;
            $scope.response = "";
        };
    }
]);
