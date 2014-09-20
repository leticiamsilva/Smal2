angular.module("ControllersModule").controller("ListLaboratoriesPresenter", [
    "$scope",
    "LaboratoryService",
    "SessionService",
    "ModalService",
    function($scope, LaboratoryService, SessionService, ModalService)
    {
        // private
        var listLaboratories = function()
        {
            LaboratoryService.getLaboratories(
                SessionService.session_id,
                function(data)
                {
                    $scope.laboratories = data;
                },
                function(data) {
                    alert("ERROR on LaboratoryService.getLaboratories():\nresponse: " + data);
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.command = listLaboratories;
            $scope.laboratories = [];

            $scope.command();
        };
    }
]);
