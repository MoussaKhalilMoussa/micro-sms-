package moussed.edu.tr.repository;

import moussed.edu.tr.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    public Optional<Course> readByTitle(String title);



}
