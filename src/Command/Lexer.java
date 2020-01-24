package Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    public List<String> lex(String line) {
        String command_name = line.substring(0, line.indexOf(' '));
        String content = line.substring(line.indexOf(' ') + 1);
        if (content.charAt(content.length() - 1) == ';') {
            //remove ";" from the end of the string
            content = content.substring(0, content.length() - 1);
        }
        List<String> resultList = new ArrayList<>();
        resultList.add(command_name);
        resultList.addAll(Arrays.asList(content.split("\\s*,\\s*")));
        return resultList;
    }
}
