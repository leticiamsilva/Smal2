angular.module("ServicesModule").factory("TicketService", [
    "$http",
    function($http)
    {
        function TicketServiceObject()
        {
            var self = this;

            // return message
            self.openTicket = function(session_id, registration, asset_code, trouble, sub_trouble, description, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/ticket/open",
                    data: {
                        session_id: session_id,
                        request : {
                            registration : registration,
                            asset_code : asset_code,
                            trouble : trouble,
                            sub_trouble : sub_trouble,
                            description : description
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
/*
            // return Array of Computers
            self.getComputers = function(session_id, laboratory_name, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/computer/list",
                    data: { session_id: session_id, request : laboratory_name },
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
            */
         }

         return new TicketServiceObject();
    }
]);
