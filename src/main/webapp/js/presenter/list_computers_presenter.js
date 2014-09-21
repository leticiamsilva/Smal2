angular.module("ControllersModule").controller("ListComputersPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "ComputerService",
    function($scope, $routeParams, SessionService, ComputerService)
    {
        // private
        var listComputers = function()
        {
            ComputerService.getComputers(
                SessionService.session_id,
                $scope.laboratory_name,
                function(data)
                {
                    $scope.computers = data;
                },
                function(data) {
                    $scope.response = data;
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.laboratory_name = $routeParams.laboratory_name;
            $scope.command = listComputers;
            $scope.computers = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
