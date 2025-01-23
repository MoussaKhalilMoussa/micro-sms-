package moussed.edu.tr.repository;

import moussed.edu.tr.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Enrollment getEnrollmentsByPersonIdAndCourseId(Integer personId, Long courseId);
    List<Enrollment> getEnrollmentsByPersonId(Integer personId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
    boolean existsByPersonIdAndCourseId(Integer personId, Long courseId);
}
