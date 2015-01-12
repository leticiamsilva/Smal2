angular.module("ServicesModule").factory("SubTroubleService", [
    "$http",
    function($http)
    {
        function SubTroubleServiceObject()
        {
            var self = this;

            // return message
            self.registerSubTrouble = function(session_id, name, trouble_name, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/subtrouble/register",
                    data: {
                        request : {
                            session_id: session_id,
                            name : name,
                            trouble_name : trouble_name
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

            // return Array of SubTroubles
            self.getSubTroubles = function(session_id, trouble_name, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/subtrouble/list",
                    data: {
                        request : {
                            session_id: session_id,
                            trouble_name : trouble_name
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
                        var subtroubles = [];

                        if(data.response != null)
                        {
                            for(var i = 0; i < data.response.length; ++i)
                            {
                                subtroubles.push(new SubTroubleModel(
                                    data.response[i].name,
                                    trouble_name
                                ));
                            }
                        }

                        fn_success(subtroubles);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };
         }

         return new SubTroubleServiceObject();
    }
]);
