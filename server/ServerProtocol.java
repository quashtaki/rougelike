import java.net.*;
import java.nio.charset.StandardCharsets;
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
                    theOutput = "Bye.";
                    byte[] outputBytes = theOutput.getBytes();
                    String encodedOutput = new String(outputBytes, StandardCharsets.UTF_8);
                    return encodedOutput;
                }
                String boardstring = game.getBoard().replace(Character.toString(player.symbol), "@");
                if(!player.alive){
                    boardstring = boardstring.substring(0,733) + "YOU DIED LOL" + boardstring.substring(745, boardstring.length());
                }
                if (theInput.equalsIgnoreCase("UP")) {
                    game.movePlayer(player, player.getX(), player.getY()-1);
                    theOutput = boardstring+player.getStats()+"END";
                }else if(theInput.equalsIgnoreCase("LEFT")) {
                    game.movePlayer(player, player.getX()-1, player.getY());
                    theOutput = boardstring+player.getStats()+"END";
                }else if(theInput.equalsIgnoreCase("DOWN")) {
                    game.movePlayer(player, player.getX(), player.getY()+1);
                    theOutput = boardstring+player.getStats()+"END";
                }else if(theInput.equalsIgnoreCase("RIGHT")) {
                    game.movePlayer(player, player.getX()+1, player.getY());
                    theOutput = boardstring+player.getStats()+"END";
                }else{
                    theOutput = boardstring+player.getStats()+"END";
                }
       }
       byte[] outputBytes = theOutput.getBytes();
       String encodedOutput = new String(outputBytes, StandardCharsets.UTF_8);
       return encodedOutput;
    }
}