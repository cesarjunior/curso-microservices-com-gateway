package curso.devdojomicroservices.token.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import curso.devdojomicroservices.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            	.sessionManagement()
            	.sessionCreationPolicy(STATELESS)
            .and()
            	.exceptionHandling()
            	.authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
            .authorizeRequests()
            .antMatchers(jwtConfiguration.getLoginUrl()).permitAll()
            .antMatchers("/course/v1/admin/**").hasRole("ADMIN")
            .antMatchers("/auth/user/**").hasAnyRole("ADMIN", "USER")
            .anyRequest().authenticated();
    }

}