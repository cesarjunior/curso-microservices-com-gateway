package curso.devdojomicroservices.token.filter;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jwt.SignedJWT;

import curso.devdojomicroservices.core.property.JwtConfiguration;
import curso.devdojomicroservices.token.converter.TokenConverter;
import curso.devdojomicroservices.token.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {
	
	protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;

	@Override
	@SneakyThrows
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain) {
		
		String header = request.getHeader(jwtConfiguration.getHeader().getName());

        if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

        SecurityContextUtil.setSecurityContext(equalsIgnoreCase("signed", jwtConfiguration.getType()) ? validate(token) : decryptValidating(token));

        chain.doFilter(request, response);
	}
	
	@SneakyThrows
    protected SignedJWT decryptValidating(String encryptedToken) {
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }

    @SneakyThrows
    protected SignedJWT validate(String signedToken) {
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }

}
