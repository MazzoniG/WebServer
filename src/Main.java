
/**
 *
 * @author Guillermo
 */
import java.net.*;

public class Main {

    ServerSocket serverSocket;

    public static void main(String[] args) throws Exception {
        new Main().runServer();
    }

    public void runServer() throws Exception {
        System.out.println("Sever is started");
        serverSocket = new ServerSocket(136);
        acceptRequests();
    }

    private void acceptRequests() throws Exception {
        while (true) {
            Socket s = serverSocket.accept();
            System.out.println(s);
            System.out.println("Hay va un thread");
            ConnectionHandler ch = new ConnectionHandler(s);
            ch.start();
            
        }
    }
}
