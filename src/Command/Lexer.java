package Command;

import java.util.Arrays;
import java.util.List;

public class Lexer {
    public List<String> lex(String line) {
        return Arrays.asList(line.split("\\s+"));
    }
}
