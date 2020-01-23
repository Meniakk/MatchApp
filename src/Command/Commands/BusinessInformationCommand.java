package Command.Commands;

import Command.ICommand;
import Server.Server;
import User.IUser;
import Visitor.CustomerCounterVisitor;

import java.util.List;

public class BusinessInformationCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        short id = Short.parseShort(line.get(1));

        IUser user = Server.getInstance().getUserByID(id);
        CustomerCounterVisitor counterVisitor = new CustomerCounterVisitor();
        Server.getInstance().accept(counterVisitor);
        return counterVisitor.toString();

    }
}
