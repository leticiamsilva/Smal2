angular.module("ServicesModule").factory("TicketService", [
    "$http",
    function($http)
    {
        function TicketServiceObject()
        {
            var self = this;

            // return message
            self.openTicket = function(session_id, asset_code, trouble, sub_trouble, description, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/ticket/open",
                    data: {
                        request : {
                            session_id: session_id,
                            asset_code : asset_code,
                            trouble : trouble,
                            sub_trouble : sub_trouble,
                            description : description
                        }
                    },
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error != null)
                    {
                        fn_error(data.error);
                    }
                    else if (data.response == null)
                    {
                        fn_error("NULL response.");
                    }
                    else
                    {
                        fn_success(data.response);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            // return Array of Tickets
            self.getTickets = function(session_id, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/ticket/list",
                    data: { session_id: session_id, request : null },
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error != null)
                    {
                        fn_error(data.error);
                    }
                    else if (data.response == null)
                    {
                        fn_error("NULL response.");
                    }
                    else
                    {
                        var tickets = [];

                        if(data.response != null)
                        {
                            for(var i = 0; i < data.response.length; ++i)
                            {
                                tickets.push(new TicketModel(
                                    data.response[i].protocol,
                                    data.response[i].open_date,
                                    data.response[i].close_date,
                                    data.response[i].description,
                                    data.response[i].user,
                                    data.response[i].technician,
                                    data.response[i].administrator,
                                    data.response[i].status,
                                    data.response[i].trouble,
                                    data.response[i].sub_trouble,
                                    data.response[i].computer
                                ));
                            }
                        }

                        fn_success(tickets);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            // return message
            self.assignTicket = function(session_id, protocol, technician, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/ticket/assign",
                    data: {
                        request : {
                            session_id: session_id,
                            protocol : protocol,
                            technician : technician
                        }
                    },
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error != null)
                    {
                        fn_error(data.error);
                    }
                    else if (data.response == null)
                    {
                        fn_error("NULL response.");
                    }
                    else
                    {
                        fn_success(data.response);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };

            // return message
            self.closeTicket = function(session_id, protocol, status, fn_success, fn_error)
            {
                $http({
                    method: "POST",
                    url: "rest/ticket/close",
                    data: {
                        request : {
                            session_id: session_id,
                            protocol : protocol,
                            status : status
                        }
                    },
                    cache: false,
                    responseType: "json"
                }).
                success(function(data, status, headers, config)
                {
                    if(data.error != null)
                    {
                        fn_error(data.error);
                    }
                    else if (data.response == null)
                    {
                        fn_error("NULL response.");
                    }
                    else
                    {
                        fn_success(data.response);
                    }
                }).
                error(function(data, status, headers, config) { fn_error("AJAX ERROR:\n" + config.method + ": " + config.url + "\nstatus: " + status + "\nresponse: " + angular.toJson(data, true)); });
            };
         }

         return new TicketServiceObject();
    }
]);
