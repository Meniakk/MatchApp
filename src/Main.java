import Command.Lexer;
import Command.Parser;
import Server.TCP_Part.TCP_Server;

public class Main {
    public static void main() {
        TCP_Server my_tcp_server = new TCP_Server(new Lexer(), new Parser());
        try {
            my_tcp_server.acceptClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
