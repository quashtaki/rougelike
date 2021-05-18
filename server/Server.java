import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Server {
    Player player1, player2;
    public Server(int portNumber){
        Game game = new Game();
        player1 = game.getPlayer1();
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
        ) {
           
        String inputLine, outputLine;
                
        // Initiate conversation with client
        ServerProtocol sp = new ServerProtocol();
        outputLine = sp.processInput(null, null, null);
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
                outputLine = sp.processInput(game, player1, inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}