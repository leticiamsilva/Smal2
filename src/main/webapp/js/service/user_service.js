angular.module("ServicesModule").factory("UserService", [
    "$http",
    function($http)
    {
        function UserServiceObject()
        {
            var self = this;

            // return message
            self.registerUser = function(session_id, registration, password, name, email, type, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/user/register",
                    data: {
                        request : {
                            session_id: session_id,
                            registration : registration,
                            password : password,
                            name : name,
                            email : email,
                            type : type
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

            // return Array of Users
            self.getUsers = function(session_id, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/user/list",
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
                        var users = [];

                        if(data.response != null)
                        {
                            for(var i = 0; i < data.response.length; ++i)
                            {
                                users.push(new UserModel(
                                    data.response[i].registration,
                                    data.response[i].name,
                                    data.response[i].type
                                ));
                            }
                        }

                        fn_success(users);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };
         }

         return new UserServiceObject();
    }
]);
