package moussed.edu.tr.service.impl;

import jakarta.transaction.Transactional;
import moussed.edu.tr.feignClient.CourseClient;
import moussed.edu.tr.feignClient.PersonClient;
import moussed.edu.tr.model.Course;
import moussed.edu.tr.model.Enrollment;
import moussed.edu.tr.model.Person;
import moussed.edu.tr.model.dto.EnrollmentDto;
import moussed.edu.tr.repository.EnrollmentRepository;
import moussed.edu.tr.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private PersonClient personClient;
    @Autowired
    private CourseClient courseClient;

    @Transactional
    public String enrollStudent(EnrollmentDto enrollmentDto) {
        // Validate the person
        ResponseEntity<Person> personResponse = personClient.getPersonById(enrollmentDto.getPersonId());
        if (personResponse.getBody() == null) {
            return "Student not found";
        }
        Person person = personResponse.getBody();

        // Validate the course
        ResponseEntity<Course> courseResponse = courseClient.getCourseById(enrollmentDto.getCourseId());
        if (courseResponse.getBody() == null) {
            return "Course not found";
        }
        Course course = courseResponse.getBody();

        // Check if enrollment already exists to prevent duplicate records
        if (enrollmentRepository.existsByPersonIdAndCourseId(enrollmentDto.getPersonId(), enrollmentDto.getCourseId())) {
            return "Student is already enrolled in this course";
        }

        // Create and save the enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setPersonId(enrollmentDto.getPersonId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setClassDate(enrollmentDto.getClassDate());
        enrollment.setState(enrollmentDto.getState());
        enrollment.setEnrollmentDate(LocalDate.now()); // Use LocalDate.now() for simplicity
        enrollment.setTuitionFee(enrollmentDto.getTuitionFee());
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        // Update the person service with enrollment info
        ResponseEntity<String> personUpdateResponse = personClient.addEnrollmentId(person.getId(), savedEnrollment.getId());
        if (!personUpdateResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("Failed to update person with enrollment ID");
        }

        // Update the course service with enrollment info
        ResponseEntity<String> courseUpdateResponse = courseClient.addEnrollmentId(savedEnrollment.getId(), course.getId());
        if (!courseUpdateResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("Failed to update course with enrollment ID");
        }

        return "Enrollment successfully completed";
    }



//    @Override
//    public String enrollStudent(EnrollmentDto enrollmentDto) {
//        ResponseEntity <Person> person = personClient.getPersonById(enrollmentDto.getPersonId());
//        ResponseEntity<Course> courseById = courseClient.getCourseById(enrollmentDto.getCourseId());
//        Enrollment enrollment = new Enrollment();
//
//        if (person.getBody() == null) {
//            return "student not found";
//        }
//        if (courseById.getBody() == null) {
//            return "course not found";
//        }
//
//        enrollment.setPersonId(enrollmentDto.getPersonId());
//        enrollment.setCourseId(enrollmentDto.getCourseId());
//        enrollment.setClassDate(enrollmentDto.getClassDate());
//        enrollment.setState(enrollmentDto.getState());
//        enrollment.setEnrollmentDate(LocalDate.now());
//        enrollment.setTuitionFee(enrollmentDto.getTuitionFee());
//        Enrollment saved = enrollmentRepository.save(enrollment);
//
//        ResponseEntity<String> stringResponseEntity1 = personClient.addEnrollmentId(person.getBody().getId(), saved.getId());
//        if (!stringResponseEntity1.getStatusCode().is2xxSuccessful()) {
//            return "An error has occurred while enrolling student";
//        }
//        ResponseEntity<String> stringResponseEntity2 = courseClient.addEnrollmentId(saved.getId(), courseById.getBody().getId());
//        if (!stringResponseEntity2.getStatusCode().is2xxSuccessful()) {
//            return "An error has occurred while assigning course to student";
//        }
//        return "Enrollment successfully";
//    }

    @Override
    public Enrollment getEnrollment(Integer personId, Long courseId) {
        return enrollmentRepository.getEnrollmentsByPersonIdAndCourseId(personId, courseId);
    }

    @Override
    public List<Enrollment> getEnrollmentByPersonId(Integer personId) {
        return enrollmentRepository.getEnrollmentsByPersonId(personId);
    }

    @Override
    public List<Enrollment> getEnrollmentByPersonUserName(String userName) {
        ResponseEntity<Person> personByUserName = personClient.getPersonByUserName(userName);
        Person person = personByUserName.getBody();
        if (person != null) {
            return enrollmentRepository.getEnrollmentsByPersonId(person.getId());
        }
        return null;
    }

    @Override
    public List<Enrollment> getEnrollmentByCourseId(Long courseId) {
        return enrollmentRepository.getEnrollmentsByCourseId(courseId);
    }
    @Override
    public List<Enrollment> getEnrollmentByCourseTitle(String courseTitle) {
        ResponseEntity<Course> courseByTitle = courseClient.getCourseByTitle(courseTitle);
        Course course = courseByTitle.getBody();

        if (course != null) {
            return enrollmentRepository.getEnrollmentsByCourseId(course.getId());
        }
        return null;
    }
    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Transactional
    @Override
    public String updateEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollmentDb = enrollmentRepository.getEnrollmentsByPersonIdAndCourseId(enrollmentDto.getPersonId(), enrollmentDto.getCourseId());
        if (enrollmentDb == null) {
            return "Enrollment not found";
        }
        enrollmentDb.setState(enrollmentDto.getState());
        enrollmentDb.setTuitionFee(enrollmentDto.getTuitionFee());
        enrollmentDb.setClassDate(enrollmentDto.getClassDate());
        enrollmentRepository.save(enrollmentDb);
        return "Enrollment successfully updated";
    }

    @Transactional
    @Override
    public String deleteEnrollment(Integer personId, Long courseId) {
        Enrollment enrollmentsByPersonIdAndCourseId = enrollmentRepository.getEnrollmentsByPersonIdAndCourseId(personId, courseId);
        if (enrollmentsByPersonIdAndCourseId == null) {
            return "Enrollment not found";
        }
        enrollmentRepository.delete(enrollmentsByPersonIdAndCourseId);
        courseClient.removeEnrollmentId(enrollmentsByPersonIdAndCourseId.getId(), courseId);
        personClient.removeEnrollmentId(personId,enrollmentsByPersonIdAndCourseId.getId());
        return "Enrollment successfully deleted";
    }

    @Override
    public String deleteEnrollmentById(Long enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
        return "Enrollment successfully deleted";
    }
}
