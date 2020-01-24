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
        short id = Short.parseShort(line.get(1));

        IUser user = Server.getInstance().getUserByID(id);

        IUser personWhoLikedUser = user.getUserThatLiked();

        if (personWhoLikedUser == null)
        {
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
