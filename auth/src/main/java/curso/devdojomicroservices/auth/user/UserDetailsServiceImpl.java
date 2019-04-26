package curso.devdojomicroservices.auth.user;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import curso.devdojomicroservices.core.model.UserApplication;
import curso.devdojomicroservices.core.repository.UserApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserApplicationRepository userApplicationRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Buscando usuario por: '{}'", username);

		UserApplication userApplication = userApplicationRepository.findByUsername(username);

		log.info("ApplicationUser found '{}'", userApplication);

		if (userApplication == null)
			throw new UsernameNotFoundException(String.format("Usuário '%s' não encontrado", username));

		return new CustomUserDetails(userApplication);
	}

	private static final class CustomUserDetails extends UserApplication implements UserDetails {

		CustomUserDetails(@NotNull UserApplication userApplication) {
			super(userApplication);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}

}
