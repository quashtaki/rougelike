import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        System.out.println(Charset.defaultCharset() + " " + System.getProperty("file.encoding"));
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.listen();
    }
}