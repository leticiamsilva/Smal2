// from http://www.thinkster.io/angularjs/9jfpSmbx1j/angularjs-sharing-data-between-controllers'
// application context
angular.module("ServicesModule").factory("SessionService", [
    function()
    {
        function SessionObject()
        {
            var self = this;
            self.session_id = null;
        }

        return new SessionObject();
    }
]);
