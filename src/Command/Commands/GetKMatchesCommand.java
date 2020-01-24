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
        short id = Short.parseShort(line.get(1));
        int k = Integer.parseInt(line.get(2));

        IMatcher matcher = new LevenshteinDistanceMatcher();
        IUser userToMatch = Server.getInstance().getUserByID(id);
        List<IUser> matches = Server.getInstance().getKMatches(userToMatch, matcher, k);
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

