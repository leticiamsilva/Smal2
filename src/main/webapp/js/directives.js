// directives module
var directivesModule = angular.module("DirectivesModule", []);



// confirmDirective
// from https://coderwall.com/p/mgtrkg
directivesModule.directive("ConfirmDirective", [
    "$http",
    "$compile",
    function($http, $compile)
    {
        return {
            link : function(scope, element, attr)
            {
                var msg = attr.confirmDirective || "Are you sure?";
                var clickAction = attr.confirmedOnclick;
                element.bind('click', function(event)
                {
                    if (window.confirm(msg))
                    {
                        scope.$eval(clickAction);
                    }
                });
            }
        };
    }
]);
