package curso.devdojomicroservices.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserApplication implements AbstractEntiity {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull()
    @Column(nullable = false)
	private String username;
	
	@NotNull()
	@ToString.Exclude
    @Column(nullable = false)
	private String password;
	
	@NotNull()
	@Builder.Default
    @Column(nullable = false)
    private String role = "USER";
	
	public UserApplication(@NotNull UserApplication userApplication) {
        this.id = userApplication.getId();
        this.username = userApplication.getUsername();
        this.password = userApplication.getPassword();
        this.role = userApplication.getRole();
    }

}
