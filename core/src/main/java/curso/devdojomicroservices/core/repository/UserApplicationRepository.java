package curso.devdojomicroservices.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import curso.devdojomicroservices.core.model.UserApplication;

public interface UserApplicationRepository extends PagingAndSortingRepository<UserApplication, Long> {
	
	UserApplication findByUsername(String username);

}
