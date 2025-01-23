package moussed.edu.tr.controller;

import moussed.edu.tr.model.Enrollment;
import moussed.edu.tr.model.dto.EnrollmentDto;
import moussed.edu.tr.repository.EnrollmentRepository;
import moussed.edu.tr.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentRepository enrollmentRepository) {
        this.enrollmentService = enrollmentService;
        this.enrollmentRepository = enrollmentRepository;
    }

    @PostMapping("/addEnrollment")
    public ResponseEntity<String> enrollStudent(@RequestBody EnrollmentDto enrollmentDto) {
        String s = enrollmentService.enrollStudent(enrollmentDto);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/getEnrollment")
    public ResponseEntity<Enrollment> getEnrollment(@RequestParam Integer personId, @RequestParam Long courseId){
        Enrollment enrollment = enrollmentService.getEnrollment(personId, courseId);
        if(enrollment == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping("/getEnrollmentByPersonId")
    public ResponseEntity<List<Enrollment>> getEnrollmentByPersonId(@RequestParam Integer personId){
        List<Enrollment> enrollmentByPerson = enrollmentService.getEnrollmentByPersonId(personId);
        if(enrollmentByPerson.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollmentByPerson);
    }

    @GetMapping("/getEnrollmentByPersonUserName")
    public ResponseEntity<List<Enrollment>> getEnrollmentByPersonUserName(@RequestParam String username){
        List<Enrollment> enrollmentByPersonName = enrollmentService.getEnrollmentByPersonUserName(username);
        if(enrollmentByPersonName.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollmentByPersonName);
    }

    @GetMapping("/getEnrollmentByCourseId")
    public ResponseEntity<List<Enrollment>> getEnrollmentByCourseId(@RequestParam Long courseId){
        List<Enrollment> enrollmentByCourseId = enrollmentService.getEnrollmentByCourseId(courseId);
        if(enrollmentByCourseId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollmentByCourseId);
    }

    @GetMapping("/getEnrollmentByCourseTitle")
    public ResponseEntity<List<Enrollment>> getEnrollmentByCourseTitle(@RequestParam String courseId){
        List<Enrollment> enrollmentByCourseTitle = enrollmentService.getEnrollmentByCourseTitle(courseId);
        if(enrollmentByCourseTitle.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollmentByCourseTitle);
    }

    @GetMapping("/getAllEnrollments")
    public ResponseEntity<List<Enrollment>> getAllEnrollments(){
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        if(enrollments.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/updateEnrollment")
    public ResponseEntity<String> updateEnrollment(@RequestBody EnrollmentDto enrollmentDto){
        String s = enrollmentService.updateEnrollment(enrollmentDto);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/deleteEnrollment")
    public ResponseEntity<String> deleteEnrollment(@RequestParam Integer personId, @RequestParam Long courseId){
        String s = enrollmentService.deleteEnrollment(personId, courseId);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/deleteEnrollmentById")
    public ResponseEntity<String> deleteEnrollmentById(@RequestParam Long enrollmenId){
        String s = enrollmentService.deleteEnrollmentById(enrollmenId);
        return ResponseEntity.ok(s);
    }

}
