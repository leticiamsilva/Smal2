angular.module("ControllersModule").controller("LaboratoryController", [
    "$scope",
    "LaboratoryService",
    "SessionService",
    "ModalService",
    function($scope, LaboratoryServiceService, SessionService, ModalService)
    {
        /*
        // constructor
        {
            $scope.session = SessionService;

            CartService.getCart(
                SessionService.user.session_id,
                function(data)
                {
                    SessionService.cart = data;
                },
                function(data) {
                    alert("ERROR on CartController.getCart():\nresponse: " + data);
            });
        };

        $scope.addProduct = function(product_id)
        {
            CartService.addProduct(
                SessionService.user.session_id,
                product_id,
                function(data)
                {
                    SessionService.cart = data;
                },
                function(data) {
                    alert("ERROR on CartController.addProduct():\nresponse: " + data);
            });
        };

        $scope.removeProduct = function(product_id)
        {
            CartService.removeProduct(
                SessionService.user.session_id,
                product_id,
                function(data)
                {
                    SessionService.cart = data;
                },
                function(data) {
                    alert("ERROR on CartController.removeProduct():\nresponse: " + data);
            });
        };

        $scope.removeAllProducts = function()
        {
            CartService.removeAllProducts(
                SessionService.user.session_id,
                function(data)
                {
                    SessionService.cart = data;
                },
                function(data) {
                    alert("ERROR on CartController.removeAllProducts():\nresponse: " + data);
            });
        };

        $scope.checkout = function(payment_id)
        {
            SessionService.payment_id = payment_id;

            var modalOptions =
            {
                closeButtonText: "Fechar",
                actionButtonText: "Confirmar",
            };

            var modalConfirm =
            {
                backdrop: true,
                keyboard: true,
                modalFade: true,
                templateUrl: "partials/checkout_confirm.html",
                controller: ModalService.getDefaultController(modalOptions, SessionService)
            };

            ModalService.showModal(modalConfirm, modalOptions).then(function (result)
            {
                CartService.checkout(
                    SessionService.user.session_id,
                    payment_id,
                    function(data)
                    {
                        var modalEnd =
                        {
                                backdrop: true,
                                keyboard: true,
                                modalFade: true,
                                templateUrl: "partials/checkout_end.html",
                                controller: ModalService.getDefaultController(modalOptions, SessionService)
                            };
                        ModalService.showModal(modalEnd, modalOptions);
                        SessionService.cart = data;
                    },
                    function(data)
                    {
                        alert("ERROR on CartController.checkout():\nresponse: " + data);
                    }
                );
            });
        };
        **/
    }
]);
