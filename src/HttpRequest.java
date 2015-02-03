
/**
 *
 * @author Guillermo
 */
public class HttpRequest {

    String filename;

    public HttpRequest(String request) {

        try {
            String lines[] = request.split("\n");
            lines = lines[0].split(" ");
            filename = lines[1];
        } catch (Exception ex) {
        }
    }
}
