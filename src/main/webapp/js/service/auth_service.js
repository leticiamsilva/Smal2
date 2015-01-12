angular.module("ServicesModule").factory("AuthService", [
    "$http",
    function($http)
    {
        function AuthServiceObject()
        {
            var self = this;

            // return SessionModel
            self.login = function(registration, password, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/auth/login",
                    data: {
                        request : {
                            registration : registration,
                            password : password
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
                        var session = new SessionModel(
                            data.response.session_id,
                            new UserModel(
                                data.response.registration,
                                data.response.name,
                                data.response.type
                            )
                        );

                        fn_success(session);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            // return message
            self.logout = function(session_id, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/auth/logout",
                    data: {
                        request : session_id
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

         return new AuthServiceObject();
    }
]);
