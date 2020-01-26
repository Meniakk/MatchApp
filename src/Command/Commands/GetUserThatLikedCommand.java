package Command.Commands;

import Command.ICommand;
import Logger.ILogger;
import Logger.Logger;
import Server.Server;
import User.IUser;

import java.util.List;

public class GetUserThatLikedCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        short id = 0;
        IUser user = null;
        IUser personWhoLikedUser = null;
        try {
            id = Short.parseShort(line.get(1));
        } catch (Exception e) {
            return "0";
        }
        if (id == 0) {
            return "0";
        } else {
            user = Server.getInstance().getUserByID(id);
            personWhoLikedUser = user.getUserThatLiked();
        }

        if (personWhoLikedUser == null) {
            Logger.getInstance().WriteToLog(
                    ILogger.LogLevel.WARNING,
                    ILogger.LogSubject.COMMAND,
                    "no one likes " + line.get(1));
            return "0";
        }

        return personWhoLikedUser.getName() + ", " + personWhoLikedUser.getShortDescription() + ", "
                + personWhoLikedUser.getLongDescription();
    }
}
