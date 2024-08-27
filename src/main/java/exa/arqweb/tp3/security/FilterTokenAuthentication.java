package exa.arqweb.tp3.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import static exa.arqweb.tp3.security.JwtManager.getJWT;
import static exa.arqweb.tp3.security.JwtManager.verifyJWT;

public class FilterTokenAuthentication extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwt = getJWT(request);
        verifyJWT(jwt);
        updateAuthentication(jwt);
        filterChain.doFilter(request, response);
    }

    private static void updateAuthentication(String jwt) {
        var securityContext = SecurityContextHolder.getContext();
        var authorities = Collections.singleton(new SimpleGrantedAuthority(JwtManager.getRoles(jwt)));
        var authenticationToken = getAuthenticationToken(authorities);
        securityContext.setAuthentication(authenticationToken);
    }

    private static AbstractAuthenticationToken getAuthenticationToken(Set<SimpleGrantedAuthority> authorities) {
        return new AbstractAuthenticationToken(authorities) {
            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }
        };
    }

}
