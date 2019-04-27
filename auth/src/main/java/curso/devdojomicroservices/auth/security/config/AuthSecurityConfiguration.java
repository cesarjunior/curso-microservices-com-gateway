package curso.devdojomicroservices.auth.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import curso.devdojomicroservices.auth.security.filter.JwtAuthenticationFilter;
import curso.devdojomicroservices.core.property.JwtConfiguration;
import curso.devdojomicroservices.token.config.TokenSecurityConfiguration;
import curso.devdojomicroservices.token.converter.TokenConverter;
import curso.devdojomicroservices.token.creator.TokenCreator;
import curso.devdojomicroservices.token.filter.JwtTokenAuthorizationFilter;

@EnableWebSecurity
public class AuthSecurityConfiguration extends TokenSecurityConfiguration {

	private final UserDetailsService userDetailsService;
	private final TokenCreator tokenCreator;
	private final TokenConverter tokenConverter;

	public AuthSecurityConfiguration(JwtConfiguration jwtConfiguration,
                                     @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     TokenCreator tokenCreator, 
                                     TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.userDetailsService = userDetailsService;
        this.tokenCreator = tokenCreator;
        this.tokenConverter = tokenConverter;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtConfiguration, tokenCreator))
			.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
