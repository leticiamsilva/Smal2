angular.module("ServicesModule").factory("AccountService", [
    "$http",
    function($http)
    {
        function AccountServiceObject()
        {
            var self = this;

            self.register = function(username, password, email, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    data: { username: username, password: password, email: email },
                    url: "rest/account/register",
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error == null)
                    {
                        var user = new UserModel(
                            data.response.session_id,
                            data.response.username,
                            data.response.email
                        );

                        fn_success(user);
                    }
                    else
                    {
                        fn_error(data.error);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            self.edit = function(session_id, username, password, email, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    data: { session_id: session_id, username: username, password: password, email: email },
                    url: "rest/account/edit",
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error == null)
                    {
                        var user = new UserModel(
                            data.response.session_id,
                            data.response.username,
                            data.response.email
                        );

                        fn_success(user);
                    }
                    else
                    {
                        fn_error(data.error);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            self.login = function(username, password, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    data: { username: username, password: password },
                    url: "rest/account/login",
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error == null)
                    {
                        var user = new UserModel(
                            data.response.session_id,
                            data.response.username,
                            data.response.email
                        );

                        fn_success(user);
                    }
                    else
                    {
                        fn_error(data.error);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            self.logout = function(session_id, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    data: { session_id: session_id },
                    url: "rest/account/logout",
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error == null)
                    {
                        fn_success(null);
                    }
                    else
                    {
                        fn_error(data.error);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            self.getSession = function(cookie_session, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    data: { session_id: cookie_session },
                    url: "rest/account/session",
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error == null)
                    {
                        var user = new UserModel(
                            data.response.session_id,
                            data.response.username,
                            data.response.email
                        );

                        fn_success(user);
                    }
                    else
                    {
                        fn_error(data.error);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };
        }

        return new AccountServiceObject();
    }
]);
