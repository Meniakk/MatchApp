package Command;

import java.util.Arrays;
import java.util.List;

public class Lexer {
    public List<String> lex(String line) {
        if (line.charAt(line.length() - 1) ==';')
        //remove ";" from the end of the string
        line = line.substring(0, line.length() - 1);
        return Arrays.asList(line.split("[\\s,]+"));
    }
}
