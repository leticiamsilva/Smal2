function TicketModel(protocol, open_date, close_date, description, user, technician, administrator, status, trouble, subTrouble, computer)
{
    var self = this;
    self.protocol = protocol;
    self.open_date = open_date;
    self.close_date = close_date;
    self.description = description;
    self.user = user;
    self.technician = technician;
    self.administrator = administrator;
    self.status = status;
    self.trouble = trouble;
    self.subTrouble = subTrouble;
    self.computer = computer;
}
