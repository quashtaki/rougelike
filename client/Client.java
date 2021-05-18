import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client{
    public Client(String[] args) throws IOException{
        String hostName = "localhost";
        int portNumber = Integer.parseInt(args[0]);
        String fromServer;
        String line;
        String fromUser;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        try (
            Socket sSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(sSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(sSocket.getInputStream()));
        ){
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;
                if (fromServer.equals("END")){
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
                    }
                }
            }
        }
    }
}
