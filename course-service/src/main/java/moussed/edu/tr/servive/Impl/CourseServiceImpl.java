package moussed.edu.tr.servive.Impl;

import moussed.edu.tr.model.Course;
import moussed.edu.tr.model.CourseDto;
import moussed.edu.tr.model.CourseMapper;
import moussed.edu.tr.repository.CourseRepository;
import moussed.edu.tr.servive.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public String addCourse(CourseDto courseDto) {
        Optional<Course> courseByTitle = courseRepository.readByTitle(courseDto.getTitle());
        if (courseByTitle.isPresent()) {
            return "Course already exists";
        }
        Course course = CourseMapper.INSTANCE.toEntity(courseDto);
        course.setTitle(course.getTitle().toUpperCase());
        courseRepository.save(course);
        return "Course added Successfully";
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> readById = courseRepository.findById(id);
        return readById.orElse(null);
    }

    @Override
    public Course getCourseByTitle(String courseTitle) {
        Optional<Course> readByTitle = courseRepository.readByTitle(courseTitle.toUpperCase());
        return  readByTitle.orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
       return courseRepository.findAll();
    }

    @Override
    public String addEnrollmentId(Long enrollmentId, Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        course.get().getEnrollmentIds().add(enrollmentId);
        courseRepository.save(course.get());
        return "Enrollment id="+enrollmentId+"added successfully to course id="+courseId;
    }

    @Override
    public String removeEnrollmentId(Long enrollId, Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        course.get().getEnrollmentIds().remove(enrollId);
        courseRepository.save(course.get());
        return "enrollment with id="+enrollId+" removed successfully from course with id="+courseId;
    }
    
    @Override
    public String updateCourseByTitle(CourseDto courseDto, String courseTitle) {
        Optional<Course> courseByTitleDb = courseRepository.readByTitle(courseTitle.toUpperCase());
        if (courseByTitleDb.isPresent()) {
            courseByTitleDb.get().setTitle(courseDto.getTitle().toUpperCase());
            courseByTitleDb.get().setDescription(courseDto.getDescription());
            courseByTitleDb.get().setSemester(courseDto.getSemester());

            courseRepository.save(courseByTitleDb.get());
            return "Course with title:"+courseTitle+" updated Successfully";
        }
        return "Course with title:"+courseTitle+" not found";
    }

    @Override
    public String updateCourseById(CourseDto courseDto, Long courseId) {
        Optional<Course> courseByIdDb = courseRepository.findById(courseId);
        if (courseByIdDb.isPresent()) {
            courseByIdDb.get().setTitle(courseDto.getTitle().toUpperCase());
            courseByIdDb.get().setDescription(courseDto.getDescription());
            courseByIdDb.get().setSemester(courseDto.getSemester());
            courseRepository.save(courseByIdDb.get());
            return "Course with id="+courseId+" updated Successfully";
        }
        return "Course with id="+courseId+" not found";
    }

    @Override
    public String deleteCourseById(Long id) {
        Optional<Course> courseById = courseRepository.findById(id);
        if (courseById.isPresent()) {
            courseRepository.deleteById(id);
            return "Course with id="+id+" deleted Successfully";
        }
        return "Course with id="+id+" not found";
    }

    @Override
    public String deleteCourseByTitle(String courseTitle) {
        Optional<Course> courseByTitle = courseRepository.readByTitle(courseTitle.toUpperCase());
        if (courseByTitle.isPresent()) {
            courseRepository.deleteById(courseByTitle.get().getId());
            return "Course with title:" + courseTitle +" deleted Successfully";
        }
        return "Course with title:"+ courseTitle +" not found";
    }
    ;
}
