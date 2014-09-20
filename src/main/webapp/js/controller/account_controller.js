angular.module("ControllersModule").controller("AccountController", [
    "$scope",
    "$routeParams",
    "$location",
    "$timeout",
    "AccountService",
    "SessionService",
    "LaboratoryService",
    function($scope, $routeParams, $location, $timeout, AccountService, SessionService, LaboratoryService)
    {
        $scope.register = function()
        {
            AccountService.register(
                $scope.form.username,
                $scope.form.password,
                $scope.form.email,
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
                            alert("ERROR on AccountController.register():\nresponse: " + data);
                    });
*/
                },
                function(data) {
                    alert("ERROR on AccountController.register():\nresponse: " + data);
                }
            );
        };

        $scope.edit = function()
        {
            AccountService.edit(
                SessionService.user.session_id,
                $scope.form.username,
                $scope.form.password,
                $scope.form.email,
                function(data)
                {
                    SessionService.user.username = data.username;
                    SessionService.user.email = data.email;
                    $location.url("/");
                },
                function(data) {
                    alert("ERROR on AccountController.edit():\nresponse: " + data);
                }
            );
        };

        $scope.login = function()
        {
            AccountService.login(
                $scope.form.username,
                $scope.form.password,
                function(data)
                {
                    SessionService.user = data;
                    document.cookie="session=" + SessionService.user.session_id;
/*
                    CartService.getCart(
                        SessionService.user.session_id,
                        function(data)
                        {
                            SessionService.cart = data;
                            $location.url("/");
                        },
                        function(data) {
                            alert("ERROR on AccountController.login():\nresponse: " + data);
                    });
*/
                },
                function(data) {
                    alert("ERROR on AccountController.login():\nresponse: " + data);
                }
            );
        };

        $scope.logout = function()
        {
            AccountService.logout(
                SessionService.user.session_id,
                function()
                {
                    $scope.form.username = SessionService.user.username;
                    document.cookie="session=";
                    delete SessionService.user;
//                    delete SessionService.cart;
                },
                function(data) {
                    alert("ERROR on AccountController.logout():\nresponse: " + data);
                }
            );
        };

        // constructor
        {
            $scope.session = SessionService;
            $scope.form = new Object();
            $scope.action = $routeParams.action;
            $scope.EMAIL_REGEXP = /^[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@[a-z0-9-]+(\.[a-z0-9-]+)*$/i;
        };
    }
]);
