package Server.Commands;

import DataBase.XMLDataBase;
import Server.ICommand;
import User.IUser;

import java.util.List;


public class IsUserRegisteredCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {

        String userIdString = line.get(1); //get the id of the user  - which is index 1 of the list
        int userId = Integer.parseInt(userIdString); //convert the string to int

        //get the user from the database.
        // null is returned if the user is not found
        IUser user = XMLDataBase.getInstance().LoadUser(userId);

        //If the user is found, return "1". Else, return "0"
        if (user != null)
            return "1";
        else
            return "0";
    }
}
