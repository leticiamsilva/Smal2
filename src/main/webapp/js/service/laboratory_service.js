angular.module("ServicesModule").factory("LaboratoryService", [
    "$http",
    function($http)
    {
        function LaboratoryServiceObject()
        {
            var self = this;

            // return Array of Laboratory
            self.getLaboratories = function(session_id, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/laboratory/list",
                    data: { session_id: session_id, request : null },
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
                        var laboratories = [];

                        if(data.response != null)
                        {
                            for(var i = 0; i < data.response.length; ++i)
                            {
                                laboratories.push(new LaboratoryModel(
                                    data.response[i].name
                                ));
                            }
                        }

                        fn_success(laboratories);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            // return message
            self.registerLaboratory = function(session_id, name, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/laboratory/register",
                    data: { session_id: session_id, request : name },
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
         }

         return new LaboratoryServiceObject();
    }
]);
