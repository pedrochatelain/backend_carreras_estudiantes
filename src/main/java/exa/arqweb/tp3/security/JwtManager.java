package exa.arqweb.tp3.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

public class JwtManager {

    private static final String SECRET_KEY = "my_secret_key"; // ⚠️ should be stored in a safe place ⚠️
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    private static final String issuer = "backend_carreras_estudiantes";
    private static final Date issuedTime = new Date();
    private static final Date expirationTime = new Date(System.currentTimeMillis() + 50000000L );

    public static String createJWT(Authentication authentication) {
        String roles = defineRoles(authentication);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(authentication.getName())
                .withClaim("roles", roles)
                .withIssuedAt(issuedTime)
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }

    private static String defineRoles(Authentication authentication) {
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority a: authentication.getAuthorities()) {
            roles.append(a.toString());
        }
        return roles.toString();
    }

    public static void verifyJWT(String jwt) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(jwt);
    }

    public static String getRoles(String jwt) {
        return JWT.decode(jwt).getClaims().get("roles").asString();
    }

    public static String getJWT(HttpServletRequest request) {
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (jwt != null) {
            jwt = jwt.replace("Bearer ", "");
        }
        return jwt;
    }

}
