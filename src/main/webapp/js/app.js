angular.module("ServicesModule", []);
angular.module("ControllersModule", []);
angular.module("FiltersModule", []);
angular.module("DirectivesModule", []);
angular.module("AnimationsModule", [
    "ngAnimate"
]);



// application module
var appModule = angular.module("AppModule", [
    "ngRoute",
    "ngSanitize",
    "ui.bootstrap",
    "ServicesModule",
    "ControllersModule",
    "FiltersModule",
    "DirectivesModule",
    "AnimationsModule"
]);



// app url routes to templates
appModule.config( [
    "$routeProvider",
    function($routeProvider)
    {
        $routeProvider./*
        when("/products",
        {
            templateUrl: "partials/product_list.html",
            controller: "ProductListController"
        }).
        when("/products/:product_id*",
        {
            templateUrl: "partials/product_details.html",
            controller: "ProductDetailController"
        }).*/
        when("/account/:action*",
        {
            templateUrl: "partials/account.html",
            auth_required: false
        }).
        when("/user/register",
        {
            templateUrl: "partials/register_user.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/user/list",
        {
            templateUrl: "partials/list_users.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/laboratory/register",
        {
            templateUrl: "partials/register_laboratory.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/laboratory/list",
        {
            templateUrl: "partials/list_laboratories.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/computer/register/:laboratory_name*",
        {
            templateUrl: "partials/register_computer.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/computer/list/:laboratory_name*",
        {
            templateUrl: "partials/list_computers.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/trouble/register",
        {
            templateUrl: "partials/register_trouble.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/trouble/list",
        {
            templateUrl: "partials/list_troubles.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/subtrouble/register/:trouble_name*",
        {
            templateUrl: "partials/register_subtrouble.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/subtrouble/list/:trouble_name*",
        {
            templateUrl: "partials/list_subtroubles.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/ticket/open/:computer_asset_code*",
        {
            templateUrl: "partials/open_ticket.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/ticket/list",
        {
            templateUrl: "partials/list_tickets.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/ticket/assign/:protocol*",
        {
            templateUrl: "partials/assign_ticket.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        when("/ticket/close/:protocol*",
        {
            templateUrl: "partials/close_ticket.html",
            //auth_required: true //TODO [CMP] re-enable
        }).
        otherwise(
        {
            redirectTo: "/laboratory/list"
        });
    }
]);



// client-site authorization check
// from http://blog.brunoscopelliti.com/deal-with-users-authentication-in-an-angularjs-web-app
appModule.run( [
    "$rootScope",
    "$location",
    "SessionService",
    function ($rootScope, $location, SessionService)
    {
        $rootScope.$on("$routeChangeStart", function(event, curr_route, prev_route)
        {
            if ((typeof(curr_route.auth_required) !== "undefined") &&
                curr_route.auth_required &&
                (typeof(SessionService.user) === "undefined"))
            {
                // reload the login route
                $location.url("/account/login");
            }
            /*
             * IMPORTANT:
             * It's not difficult to fool the previous control,
             * so it's really IMPORTANT to repeat the control also in the backend,
             * before sending back from the server reserved information.
             */
        });
    }
]);
