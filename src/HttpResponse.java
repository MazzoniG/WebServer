
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.imageio.*;
import static java.nio.charset.StandardCharsets.*;

/**
 *
 * @author Guillermo
 */
public class HttpResponse {

    HttpRequest req;
    String response;
    PrintWriter pw;
    Date date = new Date();

    public HttpResponse(HttpRequest request, PrintWriter pw, Socket sock) {
        this.req = request;
        this.pw = pw;
        response = "";
        System.out.println("Metodo poe" + req.method);
        String root = getClass().getResource("mi_web").getPath();
        File f = new File(root + req.filename);
        System.out.println(request.filename);
        if (req.method.equalsIgnoreCase("GET")) {
            String fileType = req.filename.substring(req.filename.indexOf('.') + 1, req.filename.length());
            System.out.println("wat " + fileType);
            if ("js".equals(fileType)) {
                fileType = "javascript";
            }
            try {
                response += "HTTP/1.1 200 \r\n";
                response += "Server: Java Server/1.0 \r\n";
                String contentType = "";
                if ("html".equals(fileType) || "css".equals(fileType) || "javascript".equals(fileType)) {
                    contentType = "text";
                }
                if ("jpg".equals(fileType) || "png".equals(fileType) || "tif".equals(fileType) || "gif".equals(fileType)) {
                    contentType = "image";
                }
                response += "Content-Type: " + contentType + "/" + fileType + " \r\n";
                response += "Connection: Close \r\n";
                //response += "Connection: keep-alive \r\n";
                response += "Content-Length: " + f.length() + "\r\n";
                response += "\r\n";
                if ("text".equals(contentType)) {
                    int s;
                    FileInputStream fis = new FileInputStream(f);
                    while ((s = fis.read()) != -1) {
                        response += (char) s;
                    }
                    fis.close();
                    byte[] b = response.getBytes(ISO_8859_1);
                    sock.getOutputStream().write(b);
                }
                if ("image".equals(contentType)) {
                    //response += date.toString() + "\r\n";
                    pw.write(response);
                    BufferedImage bimg;
                    bimg = ImageIO.read(f);
                    ImageIO.write(bimg, fileType.toUpperCase(), sock.getOutputStream());
                    pw.flush();
                }
                System.out.println("TOA LA RESPUESTA" + response);
            } catch (FileNotFoundException ex) {
                response = response.replace("200", "404");
            } catch (Exception ex) {
                response = response.replace("200", "500");
            }
        }
        if (req.method.equalsIgnoreCase("POST")) {
            response += "HTTP/1.1 200 \r\n";
            response += "Server: Java Server/1.0 \r\n";
            response += "Content-Type: text/html \r\n";
            response += "Connection: Close\r\n";
            try {
                int s;
                FileInputStream fis = new FileInputStream(f);
                 response += "Content-Length: " + f.length() + "\r\n";
                response += "\r\n";
                while ((s = fis.read()) != -1) {
                    response += (char) s;
                }
                fis.close();
                byte[] b = response.getBytes(ISO_8859_1);
                sock.getOutputStream().write(b);
            } catch (FileNotFoundException ex) {
                response = response.replace("200", "404");
            } catch (Exception ex) {
                response = response.replace("200", "500");
            } finally {
                System.out.println("Todo el response es " + response);

            }
        }
    }

}
