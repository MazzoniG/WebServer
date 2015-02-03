
import java.io.*;
import java.util.*;

/**
 *
 * @author Guillermo
 */
public class HttpResponse {

    HttpRequest req;
    String response;
    Date date = new Date();

    public HttpResponse(HttpRequest request) {
        this.req = request;
        String root = getClass().getResource("mi_web").getPath();
        File f = new File(root + req.filename);

        try {

            response += "HTTP/1.1 200 \r\n";
            response += "Server: Java Server/1.0 \r\n";
            //response += date.toString() + "\r\n";
            response += "Content-Type: text/html \r\n";
            response += "Connection: Close \r\n";
            response += "Content-Length: " + f.length() + "\r\n";
            response += "\r\n";

            int s;
            FileInputStream fis = new FileInputStream(f);
            while ((s = fis.read()) != -1) {
                response += (char) s;
            }
            fis.close();

        } catch (FileNotFoundException ex) {
            response = response.replace("200", "404");
        } catch (Exception ex) {
            response = response.replace("200", "500");
        }
    }
}
