package curso.devdojomicroservices.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import curso.devdojomicroservices.core.model.Course;
import curso.devdojomicroservices.core.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {
	
	private final CourseRepository courseRepository;

    public Iterable<Course> list(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
    
}
