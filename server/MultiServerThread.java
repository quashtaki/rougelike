import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
    private Socket socket = null;
    private Game game;
    private Player player;

    public MultiServerThread(Socket socket, Game game, Player player) {
        super("MultiServerThread");
        this.socket = socket;
        this.game = game;
        this.player = player;
    }
    
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
        String inputLine, outputLine;
                
        // Initiate conversation with client
        ServerProtocol sp = new ServerProtocol();
        outputLine = sp.processInput(null, null, null);
        out.println(game.getBoard()+"\nEND");

        while ((inputLine = in.readLine()) != null) {
                outputLine = sp.processInput(game, player, inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}