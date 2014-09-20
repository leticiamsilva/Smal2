// from http://weblogs.asp.net/dwahlin/archive/2013/09/18/building-an-angularjs-modal-service.aspx
angular.module("ServicesModule").factory("ModalService", [
    "$modal",
    function($modal)
    {
        function ModalServiceObject()
        {
            var self = this;

            var modalDefaults =
            {
                backdrop: true,
                keyboard: true,
                modalFade: true,
                templateUrl: "partials/modal.html"
            };

            var modalOptions =
            {
                closeButtonText: "Close",
                actionButtonText: "OK",
                headerText: "Proceed?",
                bodyText: "Perform this action?"
            };

            self.showModal = function(customModalDefaults, customModalOptions)
            {
                if (!customModalDefaults) customModalDefaults = {};
                customModalDefaults.backdrop = "static";

                return self.show(customModalDefaults, customModalOptions);
            };

            self.show = function(customModalDefaults, customModalOptions)
            {
                //Create temp objects to work with since we're in a singleton service
                var tempModalDefaults = {};
                var tempModalOptions = {};

                //Map angular-ui modal custom defaults to modal defaults defined in service
                angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

                //Map modal.html $scope custom properties to defaults defined in service
                angular.extend(tempModalOptions, modalOptions, customModalOptions);

                if (!tempModalDefaults.controller)
                {
                    tempModalDefaults.controller = self.getDefaultController(tempModalOptions, SessionService);
                }

                return $modal.open(tempModalDefaults).result;
            };

            self.getDefaultController = function(tempModalOptions, session)
            {
                return function ($scope, $modalInstance)
                {
                    $scope.session = session;
                    $scope.modalOptions = tempModalOptions;
                    $scope.modalOptions.ok = function(result)
                    {
                        $modalInstance.close(result);
                    };

                    $scope.modalOptions.close = function(result)
                    {
                        $modalInstance.dismiss("cancel");
                    };
                };
            };

            self.alert = function(message)
            {
                $modal.open(
                {
                    templateUrl: "partials/alert.html",
                    controller : function($scope, $modalInstance)
                    {
                        $scope.modalOptions =
                        {
                            closeButtonText: "Close",
                            headerText: "Proceed?",
                            bodyText: message
                        };
                        $scope.modalOptions.close = function(result)
                        {
                            $modalInstance.close(result);
                        };
                    }
                });
            };
        }

        return new ModalServiceObject();
    }
]);
