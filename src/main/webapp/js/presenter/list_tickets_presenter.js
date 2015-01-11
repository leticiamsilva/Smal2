angular.module("ControllersModule").controller("ListTicketsPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "TicketService",
    function($scope, $routeParams, SessionService, TicketService)
    {
        // private
        var listTickets = function()
        {
            TicketService.getTickets(
                SessionService.session.session_id,
                function(data)
                {
                    $scope.tickets = data;

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
            $scope.command = listTickets;
            $scope.tickets = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
