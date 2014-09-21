function Util(){};



// from http://stackoverflow.com/questions/18082/validate-numbers-in-javascript-isnumeric/1830844#1830844
Util.isNumber = function(number)
{
    return !isNaN(parseFloat(number)) && isFinite(number);
};



// from http://stackoverflow.com/questions/5465375/javascript-date-regex-dd-mm-yyyy
Util.parseDate = function(text) // format: 01/01/1970
{
    var m = text.match(/^(\d{2})\/(\d{2})\/(\d{4})$/, '$3/$2/$1');

    return (m) ? new Date(m[3], m[2]-1, m[1]) : null;
};



// from http://www.w3schools.com/js/js_cookies.asp
Util.getCookie = function(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');

    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();

        if (c.indexOf(name) == 0)
        {
            return c.substring(name.length,c.length);
        }
    }

    return "";
};



Util.newLineToHTMLbr = function(text)
{
    return text.replace(/\n/g, "<br/>");
};



Util.HTMLbrToNewLine = function(text)
{
    return text.replace(/<br\s*[\/]?>/gi, "\n");
};



Util.jsonNewlineToNewLine = function(text)
{
    return text.replace(/\\n/g, "\n");
};
