package curso.devdojomicroservices.course.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import curso.devdojomicroservices.core.property.JwtConfiguration;
import curso.devdojomicroservices.token.config.TokenSecurityConfiguration;
import curso.devdojomicroservices.token.converter.TokenConverter;
import curso.devdojomicroservices.token.filter.JwtTokenAuthorizationFilter;

@EnableWebSecurity
public class CourseSecurityConfiguration extends TokenSecurityConfiguration {
    private final TokenConverter tokenConverter;

    public CourseSecurityConfiguration(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

}