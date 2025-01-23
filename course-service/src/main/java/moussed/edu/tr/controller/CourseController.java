package moussed.edu.tr.controller;

import moussed.edu.tr.model.Course;
import moussed.edu.tr.model.CourseDto;
import moussed.edu.tr.servive.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody CourseDto courseDto) {
        String s = courseService.addCourse(courseDto);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course courseById = courseService.getCourseById(id);
        return ResponseEntity.ok(courseById);
    }

    @GetMapping("/getCourseByTitle")
    public ResponseEntity<Course> getCourseByTitle(@RequestParam String courseTitle) {
        Course courseByTitle = courseService.getCourseByTitle(courseTitle);
        return ResponseEntity.ok(courseByTitle);
    }

        @GetMapping("/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        return ResponseEntity.ok(allCourses);
    }

    @PutMapping("/updateCourseByTitle")
    public ResponseEntity<String> updateCourseByTitle(@RequestBody CourseDto courseDto, @RequestParam String courseTitle) {
        String s = courseService.updateCourseByTitle(courseDto, courseTitle);
        return ResponseEntity.ok(s);
    }

    @PutMapping("/updateCourseById/{id}")
    public ResponseEntity<String> updateCourseById(@PathVariable Long id,@RequestBody CourseDto courseDto) {
        String s = courseService.updateCourseById(courseDto,id);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/deleteCourseById/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long id) {
        String s = courseService.deleteCourseById(id);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/deleteCourseByTitle")
    public ResponseEntity<String> deleteCourseByTitle(@RequestParam String courseTitle) {
        String courseByTitle = courseService.deleteCourseByTitle(courseTitle);
        return ResponseEntity.ok(courseByTitle);
    }

    @PostMapping("/addEnrollId")
    public ResponseEntity<String> addEnrollmentId(@RequestParam Long enrollId, @RequestParam Long courseId) {
        String s = courseService.addEnrollmentId(enrollId, courseId);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/removeEnrollId")
    public ResponseEntity<String> removeEnrollmentId(@RequestParam Long enrollId,@RequestParam Long courseId) {
        String s = courseService.removeEnrollmentId(enrollId,courseId);
        return ResponseEntity.ok(s);
    }

}
