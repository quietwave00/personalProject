package blog.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

//    public boolean validateToken(String token) {
//        String userId = getUserIdFromToken(token);
//    }

    public String getUserIdFromToken(String token) {
        return JWT.decode(token).getClaim("id").asString();
    }
}
