import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Client{
    private String hostName;
    private int portNumber;
    public Client(String[] args) throws IOException, InterruptedException{
        if(args.length==1){
            hostName = "localhost";
            portNumber = Integer.parseInt(args[0]);
        }else if (args.length==2){
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        } else {throw new IllegalArgumentException("Provide (optional) ip-address and port for server");}
        Window window = new Window();
        window.open();
        run(window);
    }


    private void run(Window window) throws IOException, InterruptedException{
        String fromServer;
        String line;
        String fromUser;
        String output;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try (
            Socket sSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(sSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(sSocket.getInputStream()));
        ){
            StringBuilder sb = new StringBuilder("<html>");
            while ((fromServer = in.readLine()) != null) {
                String newline = System.lineSeparator();
                if(!(fromServer.equals("END") || fromServer.equals(newline))){
                    sb.append(fromServer + "<br>");
                }
                if (fromServer.equals("Bye."))
                    break;
                if (fromServer.equals("END")){
                    sb.append("</html>");
                    output = sb.toString();
                    sb.setLength(6); // Reset the stringbuilder to <html>
                    System.out.println(output);
                    window.setOutput(output);
                    fromUser = "";
                    Thread.sleep(50);
                    fromUser = window.getInput();
                    if (fromUser != null) {
                        out.println(fromUser);
                    }else{
                        out.println("");
                    }
                }
            }
        }
    }
}
