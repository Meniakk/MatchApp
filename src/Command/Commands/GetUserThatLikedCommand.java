package Command.Commands;

import Command.ICommand;
import Server.Server;
import User.IUser;

import java.util.List;

public class GetUserThatLikedCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        short id = Short.parseShort(line.get(1));

        IUser user = Server.getInstance().getUserByID(id);

        IUser personWhoLikedUser = user.getUserThatLiked();
        return personWhoLikedUser.getName() + ", " + personWhoLikedUser.getShortDescription() + ", "
                + personWhoLikedUser.getLongDescription();
    }
}
