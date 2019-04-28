package curso.devdojomicroservices.gateway.security.filter;

/*
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;

import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;

import curso.devdojomicroservices.core.property.JwtConfiguration;
import curso.devdojomicroservices.token.converter.TokenConverter;
import curso.devdojomicroservices.token.filter.JwtTokenAuthorizationFilter;
import curso.devdojomicroservices.token.util.SecurityContextUtil;
import lombok.SneakyThrows;
*/
public class GatewayJwtTokenAuthorizationFilter/* extends JwtTokenAuthorizationFilter*/ {
/*
    public GatewayJwtTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration, tokenConverter);
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("Duplicates")
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
    								@NonNull HttpServletResponse response, 
    								@NonNull FilterChain chain) {
    	
        String header = request.getHeader(jwtConfiguration.getHeader().getName());

        if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

        SecurityContextUtil.setSecurityContext(decryptValidating(token));

        //if (jwtConfiguration.getType().equalsIgnoreCase("signed"))
            RequestContext.getCurrentContext().addZuulRequestHeader("Authorization", header);

        chain.doFilter(request, response);
    }
    */
}