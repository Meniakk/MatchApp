package Command.Commands;

import Command.ICommand;
import Server.Server;
import User.IUser;
import Visitor.CustomerCounterVisitor;
import Visitor.IVisitor;

import java.util.List;

public class BusinessInformationCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {

        boolean isSuccess = true;
        IVisitor visitor = null;
        short id = -1;
        short tag = -1;
        IUser user = null;

        try
        {
            id = Short.parseShort(line.get(1));
            tag = Short.parseShort(line.get(2));
        }
        catch (Exception e)
        {
            isSuccess = false;
        }

        if (isSuccess)
        {
            user = Server.getInstance().getUserByID(id);
        }
        if (user == null)
        {
            isSuccess = false;
        }
        else
        {
            switch (tag)
            {
                case 1:
                    visitor = user.generateUsersCounterReport();
                    break;
            }
            if (visitor == null)
            {
                isSuccess = false;
            }
        }

        if (isSuccess)
        {
            return visitor.toString();
        }
        else
        {
            return "0";
        }
    }
}
