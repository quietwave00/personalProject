package blog.config;

import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
public class SpotifyConfig {

        private static final String CLIENT_ID = "c7664d24897f4069afbb1d61090101fc";
        private static final String CLIENT_SECRET = "646cc8380b194374bf0e75eecfbf63f8";
        private static final com.wrapper.spotify.SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(CLIENT_ID).setClientSecret(CLIENT_SECRET).build();

        public static String accessToken() {
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
            try {
                final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
                spotifyApi.setAccessToken(clientCredentials.getAccessToken());
                return spotifyApi.getAccessToken();

            } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
                System.out.println("Error: " + e.getMessage());
                return "error";
            }
        }




}
