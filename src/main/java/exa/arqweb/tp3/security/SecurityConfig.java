package exa.arqweb.tp3.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import exa.arqweb.tp3.service.UsuarioService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.*;

import static exa.arqweb.tp3.security.JwtManager.createJWT;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

//@EnableWebSecurity(debug = true)
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SecurityConfig {

    private final UsuarioService usuarioService;

    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean UserDetailsService userDetailsService() {


        return username -> {
            var usuario = usuarioService.getUser(username);
            return User.withUsername(
                     usuario.getNombre())
                    .password(usuario.getPassword())
                    .roles(usuario.getRoles()
            ).build();
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String jwt = createJWT(SecurityContextHolder.getContext().getAuthentication());
            var body = new HashMap<String, Object>();
            body.put("message", "Succesfully logged in");
            body.put("token", jwt);
            new ObjectMapper().writeValue(response.getWriter(), body);
        };
    }

    @Bean
    SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(new HandlerMappingIntrospector());
        http.securityMatcher("/api/**")
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new FilterTokenAuthentication(), AnonymousAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(mvc.pattern(HttpMethod.POST, "/api/estudiantes")).hasRole("ADMIN")
                .requestMatchers(mvc.pattern(HttpMethod.GET, "/api/estudiantes")).hasAnyRole("ADMIN", "USER")
                .requestMatchers(mvc.pattern(HttpMethod.POST, "/api/carreras")).hasRole("ADMIN")
                .requestMatchers(mvc.pattern(HttpMethod.GET, "/api/carreras")).hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
        ;
        return http.build();
    }

    @Bean
    SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/login"))
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(config -> config.successHandler(successHandler()))
            .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }

}