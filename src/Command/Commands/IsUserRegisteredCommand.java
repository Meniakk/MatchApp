package Command.Commands;

import Command.ICommand;
import Server.Server;

import java.util.List;


public class IsUserRegisteredCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {

        String userIdString = line.get(1); //get the id of the user  - which is index 1 of the list
        short userId = Short.parseShort(userIdString); //convert the string to int

        //get the user from the database.
        // null is returned if the user is not found
        boolean isExist = Server.getInstance().isUserExist(userId);

        //If the user is found, return "1". Else, return "0"
        return isExist ? "1" : "0";
    }
}
