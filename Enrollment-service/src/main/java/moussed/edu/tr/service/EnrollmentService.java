package moussed.edu.tr.service;

import moussed.edu.tr.model.Enrollment;
import moussed.edu.tr.model.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {

    public String enrollStudent(EnrollmentDto enrollmentDto);
    public Enrollment getEnrollment(Integer personId, Long courseId);
    public List<Enrollment> getEnrollmentByPersonId(Integer personId);
    public List<Enrollment> getEnrollmentByPersonUserName(String personUserName);
    public List<Enrollment> getEnrollmentByCourseId(Long courseId);
    public List<Enrollment> getEnrollmentByCourseTitle(String courseTitle);
    public List<Enrollment> getAllEnrollments();
    public String updateEnrollment(EnrollmentDto enrollmentDto);
    public String deleteEnrollment(Integer personId, Long courseId);
    String deleteEnrollmentById(Long enrollmentId);
}
