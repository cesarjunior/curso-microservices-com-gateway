package curso.devdojomicroservices.auth.security.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;

import curso.devdojomicroservices.core.model.UserApplication;
import curso.devdojomicroservices.core.property.JwtConfiguration;
import curso.devdojomicroservices.token.creator.TokenCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
	
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;
    private final TokenCreator tokenCreator;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("Attempting authentication. . .");
        UserApplication userApplication = new ObjectMapper().readValue(request.getInputStream(), UserApplication.class);

        if (userApplication == null)
            throw new UsernameNotFoundException("Unable to retrieve the username or password");

        log.info("Creating the authentication object for the user '{}' and calling UserDetailServiceImpl loadUserByUsername", userApplication.getUsername());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userApplication.getUsername(), userApplication.getPassword(), Collections.emptyList());
        usernamePasswordAuthenticationToken.setDetails(userApplication);

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest request, 
    										HttpServletResponse response, 
    										FilterChain chain, 
    										Authentication auth) {
    	
        log.info("Authentication was successful for the user '{}', generating JWE token", auth.getName());

        SignedJWT signedJWT = tokenCreator.createSignedJWT(auth);

        String encryptedToken = tokenCreator.encryptToken(signedJWT);

        log.info("Token generated successfully, adding it to the response header");

        response.addHeader("Access-Control-Expose-Headers", "XSRF-TOKEN, " + jwtConfiguration.getHeader().getName());

        response.addHeader(jwtConfiguration.getHeader().getName(), jwtConfiguration.getHeader().getPrefix() + encryptedToken);
    }

}
