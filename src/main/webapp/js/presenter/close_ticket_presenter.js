angular.module("ControllersModule").controller("CloseTicketPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "TicketService",
    function($scope, $routeParams, SessionService, TicketService)
    {
        // private
        var closeTicket = function()
        {
            TicketService.closeTicket(
                SessionService.session.session_id,
                $scope.protocol,
                $scope.technician,
                $scope.status,
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
            $scope.protocol = $routeParams.protocol;
            $scope.technician = "";
            $scope.status = "RESOLVED";
            $scope.command = closeTicket;
            $scope.response = "";
        };
    }
]);
