
/**
 *
 * @author Guillermo
 */
import java.net.*;
import java.io.*;

public class ConnectionHandler extends Thread {

    private Socket s;
    PrintWriter pw;
    BufferedReader br;

    public ConnectionHandler(Socket s) throws Exception {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw = new PrintWriter(s.getOutputStream());
    }

    @Override
    public void run() {

        try {
            String requestString = "";

            while (br.ready() || requestString.length() == 0) {
                //System.out.println(requestString);
                requestString += (char) br.read();
            }
            if (requestString.contains("Connection: keep-alive")) {
                s.setKeepAlive(true);
            }
            //System.out.println(requestString);
            HttpRequest req = new HttpRequest(requestString);
            System.out.println("la request es "  + requestString);
            System.out.println("yaaa");
            HttpResponse res = new HttpResponse(req,pw,s);

            //pw.write(res.response.toCharArray());
            //pw.close();
            //br.close();
            //System.out.println(s.isClosed());
            s.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
