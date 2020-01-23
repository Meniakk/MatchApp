package Command.Commands;

import Command.ICommand;
import Server.Server;
import User.IUser;

import java.util.List;

public class LikeCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        try {
            short id = Short.parseShort(line.get(1));
            IUser user = Server.getInstance().getUserByID(id);

            short idOther = Short.parseShort(line.get(2));
            IUser otherUser = Server.getInstance().getUserByID(id);

            user.addLikedUser(otherUser);
            otherUser.addLikedBy(user);

            return "1";
        } catch (Exception e) {
            return "0";
        }

    }
}
