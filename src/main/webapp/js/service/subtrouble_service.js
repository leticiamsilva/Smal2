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
                        session_id: session_id,
                        request : {
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
         }

         return new SubTroubleServiceObject();
    }
]);
