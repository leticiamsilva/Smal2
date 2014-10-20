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
                SessionService.session_id,
                $scope.protocol,
                $scope.administrator,
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
            $scope.administrator = "";
            $scope.technician = "";
            $scope.command = assignTicket;
            $scope.response = "";
        };
    }
]);
