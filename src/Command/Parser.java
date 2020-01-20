package Command;

import java.util.*;

public class Parser {
    private Map<String, ICommand<String>> commands;

    public Parser() {
        commands = new HashMap<>();
    }

    public String parse(List<String> line) {

        String commandName = line.get(0);
        ICommand<String> command = commands.get(commandName);
        String result = command.doCommand(line);
        return result;
    }

}
