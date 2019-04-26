package curso.devdojomicroservices.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"curso.devdojomicroservices.core.model"})
@EnableJpaRepositories({"curso.devdojomicroservices.core.repository"})
public class CourseApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
	
}
