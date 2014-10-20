// filters module
var filtersModule = angular.module("FiltersModule", []);



// CheckmarkFilter filter
filtersModule.filter("CheckmarkFilter",
    function()
    {
        return function(bool_text)
        {
            return bool_text ? "\u2713" : "\u2718";
        };
    }
);



filtersModule.filter("NewLineToHTMLbrFilter",
    function()
    {
        return function(text)
        {
            if(typeof(text) === "undefined")
            {
                return "";
            }

            return Util.newLineToHTMLbr(text);
        };
    }
);



filtersModule.filter("HTMLbrToNewLineFilter",
    function()
    {
        return function(text)
        {
            if(typeof(text) === "undefined")
            {
                return "";
            }

            return Util.HTMLbrToNewLine(text);
        };
    }
);



filtersModule.filter("JsonNewlineToNewLineFilter",
    function()
    {
        return function(text)
        {
            if(typeof(text) === "undefined")
            {
                return "";
            }

            return Util.jsonNewlineToNewLine(text);
        };
    }
);



filtersModule.filter("ImagePathForComputer", [
    "SessionService",
    function (SessionService)
    {
        return function(computer)
        {
            if(typeof(computer) === "undefined")
            {
                return "img/icon/monitor_transparent32.png";
            }

            return "img/icon/monitor32.png";
        };
    }
]);



filtersModule.filter("NameForComputer", [
    "SessionService",
    function (SessionService)
    {
        return function(computer)
        {
            if(typeof(computer) === "undefined")
            {
                return "[empty]";
            }

            return computer.asset_code;
        };
    }
]);



filtersModule.filter("ReferenceURLFilter", [
    "SessionService",
    function (SessionService)
    {
        return function(product)
        {
            if(typeof(product) === "undefined")
            {
                return "";
            }

            return SessionService.getReferenceURLFromProduct(product);
        };
    }
]);
