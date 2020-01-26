package Command.Commands;

import Command.ICommand;
import Logger.ILogger;
import Logger.Logger;
import Server.Server;
import User.IUser;
import Logger.ILogger.LogLevel;

import java.util.List;

public class LikeCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        try {
            short id = 0;
            short idOther = 0;
            IUser user = null;
            IUser otherUser = null;

            try {
                id = Short.parseShort(line.get(1));
                user = Server.getInstance().getUserByID(id);

                idOther = Short.parseShort(line.get(2));
                otherUser = Server.getInstance().getUserByID(idOther);
            } catch (Exception e) {
                return "0";
            }

            user.addLikedUser(otherUser);
            otherUser.addLikedBy(user);

            Logger.getInstance().WriteToLog(LogLevel.INFO, ILogger.LogSubject.COMMAND,
                    "LikeCommand: Marked user liked by other user");
            return "1";
        } catch (Exception e) {
            Logger.getInstance().WriteToLog(LogLevel.INFO, ILogger.LogSubject.COMMAND,
                    "LikeCommand: An error occurred");
            return "0";
        }

    }
}
