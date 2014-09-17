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



filtersModule.filter("ImagePathFilter", [
    "SessionService",
    function (SessionService)
    {
        return function(product)
        {
            if(typeof(product) === "undefined")
            {
                return "";
            }

            return SessionService.getImagePathFromProduct(product);
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
