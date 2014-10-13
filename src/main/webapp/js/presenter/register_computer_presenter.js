angular.module("ControllersModule").controller("RegisterComputerPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "ComputerService",
    function($scope, $routeParams, SessionService, ComputerService)
    {
        // private
        var registerComputer = function()
        {
            ComputerService.registerComputer(
                SessionService.session_id,
                $scope.asset_code,
                $scope.laboratory_name,
                $scope.row_num,
                $scope.column_num,
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
            $scope.asset_code = "";
            $scope.laboratory_name = $routeParams.laboratory_name;
            $scope.row_num = "";
            $scope.column_num = "";
            $scope.command = registerComputer;
            $scope.response = "";
        };
    }
]);
