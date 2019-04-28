package curso.devdojomicroservices.gateway.security.config;

/*
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import curso.devdojomicroservices.core.property.JwtConfiguration;
import curso.devdojomicroservices.gateway.security.filter.GatewayJwtTokenAuthorizationFilter;
import curso.devdojomicroservices.token.config.TokenSecurityConfiguration;
import curso.devdojomicroservices.token.converter.TokenConverter;

@EnableWebSecurity
*/
public class GatewaySecurityConfiguration/* extends TokenSecurityConfiguration*/ {
	/*
	private final TokenConverter tokenConverter;

    public GatewaySecurityConfiguration(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
    */
}
