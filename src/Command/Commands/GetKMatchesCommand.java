package Command.Commands;

import Command.ICommand;
import Matcher.IMatcher;
import Matcher.*;
import User.IUser;

import java.util.List;

import Server.Server;

public class GetKMatchesCommand implements ICommand<String> {

    @Override
    public String doCommand(List<String> line) {
        short id = 0;
        int k = 0;
        boolean isSuccess = true;
        IUser userToMatch = null;
        IMatcher matcher = null;
        List<IUser> matches = null;
        try {
            id = Short.parseShort(line.get(1));
            k = Integer.parseInt(line.get(2));
        } catch (Exception e) {
            isSuccess = false;
        }
        if (isSuccess) {
            userToMatch = Server.getInstance().getUserByID(id);
        }
        if (userToMatch != null) {
            matcher = new LevenshteinDistanceMatcher();
            matches = Server.getInstance().getKMatches(userToMatch, matcher, k);
        }

        if (matches != null) {
            StringBuilder resultStringBuilder = new StringBuilder();
            for (IUser match : matches) {
                String name;
                String shortDescription;
                String longDescription;

                name = match.getName();
                shortDescription = match.getShortDescription();
                longDescription = match.getLongDescription();

                resultStringBuilder.append(name).append(",").append(shortDescription).append(",").append(longDescription)
                        .append("~");
            }
            //remove the last "~"
            resultStringBuilder.setLength(resultStringBuilder.length() - 1);
            return resultStringBuilder.toString();
        }
        return "0";
    }

}

