package Server;

import java.util.*;

public class Parser {
    private Map<String, ICommand<String>> commands;

    public Parser() {
        commands = new HashMap<>();
    }

    public ICommand<String> parse(List<String> line) {

        String commandName = line.get(0);
        ICommand<String> command = commands.get(commandName);

        return command;
    }

}
