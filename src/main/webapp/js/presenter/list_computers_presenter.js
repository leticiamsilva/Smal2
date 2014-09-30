angular.module("ControllersModule").controller("ListComputersPresenter", [
    "$scope",
    "$routeParams",
    "SessionService",
    "ComputerService",
    function($scope, $routeParams, SessionService, ComputerService)
    {
        // private
        var listPositions = function()
        {
            ComputerService.getComputers(
                SessionService.session_id,
                $scope.laboratory_name,
                function(data) // ComputerModels
                {
                    // get num of cols and rows
                    var num_rows = 0;
                    var num_columns = 0;

                    for(var i = 0; i < data.length; ++i)
                    {
                        if (data[i].row_num > num_rows)
                        {
                            num_rows = data[i].row_num;
                        }

                        if (data[i].column_num > num_columns)
                        {
                            num_columns = data[i].column_num;
                        }
                    }

                    num_rows++; // data.row_num 0 index based
                    num_columns++; // data.column_num 0 index based

                    // create all empty positions
                    for(var i = 0; i < num_rows; ++i)
                    {
                        for(var j = 0; j < num_columns; ++j)
                        {
                            $scope.positions[i * num_columns + j] = {};
                        }
                    }

                    // assign computers to its pos
                    for(var i = 0; i < data.length; ++i)
                    {
                        $scope.positions[data[i].row_num * num_columns + data[i].column_num].computer = data[i];
                    }

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
            $scope.command = listPositions;
            $scope.positions = [];
            $scope.response = "";

            $scope.command();
        };
    }
]);
