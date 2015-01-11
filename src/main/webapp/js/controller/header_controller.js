angular.module("ControllersModule").controller("HeaderController", [
    "$scope",
    "$location",
    "SessionService",
    "AuthService",
    function HeaderController($scope, $location, SessionService, AuthService)
    {
        $scope.isActive = function(view_location)
        {
            return view_location === $location.path();
        };

        // constructor
        {
            $scope.session = SessionService;

            /*
            // Disabled code
            // useful to restore previous saved session after reopen a closed logged browser
            if (typeof(SessionService.session) === "undefined")
            {
                var cookie_session = Util.getCookie("session");

                if (cookie_session != "")
                {
                    AuthService.getSession(
                        cookie_session,
                        function(data)
                        {
                            SessionService.session = data;
                        },
                        function(data) {
                            alert("ERROR on HeaderController.getSession():\nresponse: " + data);
                        }
                    );
                }
            }
            */
        }
    }
]);
