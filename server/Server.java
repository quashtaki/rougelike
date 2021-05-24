import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Server {
    private Player[] players = new Player[9];
    private int playerCount = 0;
    private int portNumber;
    private Game game;
    public Server(int portNumber){
        this.portNumber = portNumber;
        this.game = new Game();
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
    	playerCount++;
    	players[playerCount-1] = new Player(1+playerCount,20,Integer.toString(playerCount).charAt(0));
    	game.addPlayer(players[playerCount-1],1+playerCount,20);
        return players[playerCount-1];
    }
}