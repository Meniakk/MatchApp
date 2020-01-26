package Command.Commands;

import Command.ICommand;
import Server.Server;

import java.util.List;


public class IsUserRegisteredCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        short userId = 0;
        try {
            String userIdString = line.get(1);
            userId = Short.parseShort(userIdString);
        } catch (Exception e) {
            return "0";
        }

        boolean isExist = Server.getInstance().isUserExist(userId);


        return isExist ? "1" : "0";
    }
}
