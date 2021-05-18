import java.net.*;
import java.io.*;

public class ServerProtocol{
    private static final int WAITING = 0;
    private static final int ACTION = 1;
    private int state = WAITING;

    public String processInput(Game game, Player player, String theInput) {
           String theOutput = null;
           if (state == WAITING) {
                theOutput = "END";
                state = ACTION;
            }else if (state == ACTION) {
                if(theInput.equalsIgnoreCase("Bye.")){
                    return "Bye.";
                }
                if (theInput.equalsIgnoreCase("W")) {
                    game.movePlayer(player, player.getX(), player.getY()-1);
                    theOutput = game.getBoard()+"\nEND";
                }else if(theInput.equalsIgnoreCase("A")) {
                    game.movePlayer(player, player.getX()-1, player.getY());
                    theOutput = game.getBoard()+"\nEND";
                }else if(theInput.equalsIgnoreCase("S")) {
                    game.movePlayer(player, player.getX(), player.getY()+1);
                    theOutput = game.getBoard()+"\nEND";
                }else if(theInput.equalsIgnoreCase("D")) {
                    game.movePlayer(player, player.getX()+1, player.getY());
                    theOutput = game.getBoard()+"\nEND";
                }else if(theInput.equalsIgnoreCase(" ")) {
                    theOutput = game.getBoard()+"\nEND";
                }else{
                    theOutput = game.getBoard()+"\nEND";
                }
       }
       return theOutput;
    }
}