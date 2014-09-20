angular.module("ControllersModule").controller("HeaderController", [
    "$scope",
    "$location",
    "SessionService",
    "AccountService",
    "LaboratoryService",
    function HeaderController($scope, $location, SessionService, AccountService, LaboratoryService)
    {
        $scope.isActive = function(view_location)
        {
            return view_location === $location.path();
        };

        // constructor
        {
            $scope.session = SessionService;

            SessionService.order_prop = "name";

            if (typeof(SessionService.user) === "undefined")
            {
                var cookie_session = Util.getCookie("session");

                if (cookie_session != "")
                {
                    AccountService.getSession(
                        cookie_session,
                        function(data)
                        {
                            SessionService.user = data;
/*
                            CartService.getCart(
                                SessionService.user.session_id,
                                function(data)
                                {
                                    SessionService.cart = data;
                                    $location.url("/");
                                },
                                function(data) {
                                    alert("ERROR on HeaderController.getSession():\nresponse: " + data);
                            });
*/
                        },
                        function(data) {
                            alert("ERROR on HeaderController.getSession():\nresponse: " + data);
                        }
                    );
                }
            }
        }
    }
]);
