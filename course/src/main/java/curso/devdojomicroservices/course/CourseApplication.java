package curso.devdojomicroservices.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import curso.devdojomicroservices.core.property.JwtConfiguration;

@SpringBootApplication
@EntityScan({"curso.devdojomicroservices.core.model"})
@EnableJpaRepositories({"curso.devdojomicroservices.core.repository"})
@ComponentScan("curso.devdojomicroservices")
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class CourseApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
	
}
