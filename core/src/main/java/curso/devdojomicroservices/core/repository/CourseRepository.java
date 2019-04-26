package curso.devdojomicroservices.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import curso.devdojomicroservices.core.model.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
