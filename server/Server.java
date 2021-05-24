import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Server {
    private Player player1, player2;
    private int players = 0;
    private int portNumber;
    private Game game;
    public Server(int portNumber){
        this.portNumber = portNumber;
        this.game = new Game();
        player1 = game.getPlayer1();
        player2 = game.getPlayer2();
    }
    public void listen(){
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (true) {
                new MultiServerThread(serverSocket.accept(), game, getNextPlayer()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

    // :)
    private Player getNextPlayer() {
        if (players++ == 0) return player1;
        else return player2;
    }
}