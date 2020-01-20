package Server.TCP_Part;

import Command.Lexer;
import Command.Parser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {
    private int port;
    private Lexer lexer;
    private Parser parser;
    private boolean stay_alive = true;

    public TCP_Server(Lexer lexer, Parser parser) {
        int defaultPort = 1234;
        this.port = defaultPort;
        this.lexer = lexer;
        this.parser = parser;
    }

    public TCP_Server(int port, Lexer lexer, Parser parser) {
        this.lexer = lexer;
        this.parser = parser;
    }

    public void acceptClients() throws Exception {
        String clientSentence;
        ServerSocket welcomeSocket = new ServerSocket(port);

        while (this.stay_alive) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);

            String returnValue = parser.parse(lexer.lex(clientSentence));
            outToClient.writeBytes(returnValue);
        }
    }
    //todo close the connection after each resonse

    public void close() {
        this.stay_alive = false;
    }
}
