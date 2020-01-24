package Command;

import Command.Commands.*;

import java.util.*;

public class Parser {
    private Map<String, ICommand<String>> commands;

    public Parser() {
        commands = new HashMap<>();
        commands.put("is_user", new IsUserRegisteredCommand());
        commands.put("new_user", new RegisterNewUserCommand());
        commands.put("get", new GetKMatchesCommand());
        commands.put("love", new LikeCommand());
        commands.put("loved", new GetUserThatLikedCommand());
        commands.put("bi", new BusinessInformationCommand());
    }

    public String parse(List<String> line) {

        String commandName = line.get(0);
        ICommand<String> command = commands.get(commandName);
        String result;
        if (command == null) {
            result = "0";
        } else {
            result = command.doCommand(line);
        }
        return result + ";";
    }

}
