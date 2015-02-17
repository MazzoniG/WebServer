
/**
 *
 * @author Guillermo
 */
public class HttpRequest {

    String filename;
    String method;

    public HttpRequest(String request) {

        try {
            String lines[] = request.split("\n");
            lines = lines[0].split(" ");
            filename = lines[1];
            method = lines[0];
        } catch (Exception ex) {
        }
    }
}
