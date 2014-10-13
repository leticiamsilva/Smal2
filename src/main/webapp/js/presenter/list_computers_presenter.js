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

                    positions = [];

                    // create all empty positions
                    for(var i = 0; i < num_rows; ++i)
                    {
                        for(var j = 0; j < num_columns; ++j)
                        {
                            positions[i * num_columns + j] = {};
                        }
                    }

                    // assign computers to its pos
                    for(var i = 0; i < data.length; ++i)
                    {
                        positions[(data[i].row_num - 1) * num_columns + (data[i].column_num - 1)].computer = data[i];
                    }

                    $scope.num_columns = num_columns;
                    $scope.col_size = 12 / num_columns; //[CMP] bootstrap magic number 12
                    $scope.positions = positions;
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
            //[CMP] bootstrap magic number 12
            $scope.num_columns = 6;
            $scope.col_size = 2;
            $scope.response = "";

            $scope.command();
        };
    }
]);
