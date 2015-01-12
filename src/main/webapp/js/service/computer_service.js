angular.module("ServicesModule").factory("ComputerService", [
    "$http",
    function($http)
    {
        function ComputerServiceObject()
        {
            var self = this;

            // return message
            self.registerComputer = function(session_id, asset_code, laboratory_name, row_num, column_num, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/computer/register",
                    data: {
                        request : {
                            session_id: session_id,
                            asset_code : asset_code,
                            laboratory_name : laboratory_name,
                            row_num : row_num,
                            column_num : column_num
                        }
                    },
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error != null)
                    {
                        fn_error(data.error);
                    }
                    else if (data.response == null)
                    {
                        fn_error("NULL response.");
                    }
                    else
                    {
                        fn_success(data.response);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            // return Array of Computers
            self.getComputers = function(session_id, laboratory_name, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/computer/list",
                    data: {
                        request : {
                            session_id: session_id,
                            laboratory_name : laboratory_name
                        }
                    },
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error != null)
                    {
                        fn_error(data.error);
                    }
                    else if (data.response == null)
                    {
                        fn_error("NULL response.");
                    }
                    else
                    {
                        var computers = [];

                        if(data.response != null)
                        {
                            for(var i = 0; i < data.response.length; ++i)
                            {
                                computers.push(new ComputerModel(
                                    data.response[i].asset_code,
                                    data.response[i].row_num,
                                    data.response[i].column_num
                                ));
                            }
                        }

                        fn_success(computers);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };
         }

         return new ComputerServiceObject();
    }
]);
