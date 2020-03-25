package App;

//import standard
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

//import third party
import org.json.*;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class App {

    public static void main(final String[] args) throws Exception {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet("https://corona.lmao.ninja/all");

//            // Create a custom response handler
            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {

                @Override
                public String handleResponse(
                    final ClassicHttpResponse response) throws IOException {
                        final int status = response.getCode();
    //                    System.out.println("Response code: " + status);
                        if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                            final HttpEntity entity = response.getEntity();
                            try {
                                return entity != null ? EntityUtils.toString(entity) : null;
                            } catch (final ParseException ex) {
                                throw new ClientProtocolException(ex);
                        }
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                }

            };
            final String responseBody = httpclient.execute(httpget, responseHandler);

            JSONObject jsonResponse = new JSONObject(responseBody);
            Covid19Class update = new Covid19Class(jsonResponse.getInt("cases"),
                                                 jsonResponse.getInt("deaths"),
                                                 jsonResponse.getInt("recovered"),
                                                 jsonResponse.getFloat("updated"));
            DbInterface testItem = new DbInterface();
            testItem.insert("../../updates.db", update.getCases(), update.getDeaths(), update.getRecovered(), update.getUpdated());
            Covid19Class printItem = testItem.retrieveLatest();

            System.out.print("   \nGLOBAL\n"
                    + "----------|----------------\n"
                    + "    Cases | " + printItem.getCases() + "\n"
                    + "   Losses | " + printItem.getDeaths() + "\n"
                    + "Recovered | " + printItem.getRecovered() + "\n"
                    + "  Updated | " + printItem.getUpdated() + "\n\n");

        }
    }
}