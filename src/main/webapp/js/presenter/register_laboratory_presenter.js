angular.module("ControllersModule").controller("RegisterLaboratoryPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "LaboratoryService",
    function($scope, $routeParams, SessionService, LaboratoryService)
    {
        // private
        var registerLaboratory = function()
        {
            LaboratoryService.registerLaboratory(
                SessionService.session_id,
                $scope.name,
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
            $scope.name = "";
            $scope.command = registerLaboratory;
            $scope.response = "";
        };
    }
]);
