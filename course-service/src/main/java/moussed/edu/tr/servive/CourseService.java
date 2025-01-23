package moussed.edu.tr.servive;

import moussed.edu.tr.model.Course;
import moussed.edu.tr.model.CourseDto;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public String addCourse(CourseDto courseDto);
    public Course getCourseById(Long id);
    public Course getCourseByTitle(String courseTitle);
    public List<Course> getAllCourses();
    public String addEnrollmentId(Long enrollmentId, Long courseId);
    public String updateCourseByTitle(CourseDto courseDto, String courseTitle);
    public String updateCourseById(CourseDto courseDto, Long courseId);
    public String deleteCourseById(Long id);
    public String deleteCourseByTitle(String courseTitle);
    public String removeEnrollmentId(Long enrollId, Long courseId);
}
