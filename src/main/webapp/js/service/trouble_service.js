angular.module("ServicesModule").factory("TroubleService", [
    "$http",
    function($http)
    {
        function TroubleServiceObject()
        {
            var self = this;

            // return message
            self.registerTrouble= function(session_id, name, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/trouble/register",
                    data: {
                        request : {
                            session_id: session_id,
                            name : name
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

            // return Array of Troubles
            self.getTroubles = function(session_id, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/trouble/list",
                    data: {
                        request : {
                            session_id: session_id
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
                        var troubles = [];

                        if(data.response != null)
                        {
                            for(var i = 0; i < data.response.length; ++i)
                            {
                                troubles.push(new TroubleModel(
                                    data.response[i].name
                                ));
                            }
                        }

                        fn_success(troubles);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };
         }

         return new TroubleServiceObject();
    }
]);
