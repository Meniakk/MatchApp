package Server.TCP_Part;

import Command.Lexer;
import Command.Parser;
import Logger.ILogger;
import Logger.Logger;

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
        this.port = port;
        this.lexer = lexer;
        this.parser = parser;
    }

    public void acceptClients() throws Exception {
        String clientSentence;
        ServerSocket welcomeSocket = new ServerSocket(port);
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        while (this.stay_alive)
        {
            clientSentence = inFromClient.readLine();
            if (clientSentence == null)
            {
                close();
                continue;
            }

            Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO,
                    ILogger.LogSubject.SERVER,
                    "Received: " + clientSentence);

            String returnValue = parser.parse(lexer.lex(clientSentence));

            Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO,
                    ILogger.LogSubject.SERVER,
                    "Returned: " + returnValue);

            outToClient.writeBytes(returnValue);
        }
        welcomeSocket.close();
    }

    private void close()
    {
        this.stay_alive = false;
    }
}
