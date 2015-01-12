angular.module("ControllersModule").controller("AssignTicketPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "TicketService",
    function($scope, $routeParams, SessionService, TicketService)
    {
        // private
        var assignTicket = function()
        {
            TicketService.assignTicket(
                SessionService.session.session_id,
                $scope.protocol,
                $scope.technician,
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
            $scope.command = assignTicket;
            $scope.response = "";
        };
    }
]);
