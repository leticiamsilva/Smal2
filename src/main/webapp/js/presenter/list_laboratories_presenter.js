angular.module("ControllersModule").controller("ListLaboratoriesPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "LaboratoryService",
    function($scope, $routeParams, SessionService, LaboratoryService)
    {
        // private
        var listLaboratories = function()
        {
            LaboratoryService.getLaboratories(
                SessionService.session.session_id,
                function(data)
                {
                    $scope.laboratories = data;

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
            $scope.command = listLaboratories;
            $scope.laboratories = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
