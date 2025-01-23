package moussed.edu.tr.feignClient;

import moussed.edu.tr.config.FeignClientConfig;
import moussed.edu.tr.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "course-app", url = "http://course-app:2000/api/course/", configuration = FeignClientConfig.class)
public interface CourseClient {

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id);

    @GetMapping("/getCourseByTitle")
    public ResponseEntity<Course> getCourseByTitle(@RequestParam String courseTitle);

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourses() ;

    @PostMapping("/addEnrollId")
    public ResponseEntity<String> addEnrollmentId(@RequestParam Long enrollId, @RequestParam Long courseId);

    @PostMapping("/removeEnrollId")
    public ResponseEntity<String> removeEnrollmentId(@RequestParam Long enrollId, @RequestParam Long courseId);
}