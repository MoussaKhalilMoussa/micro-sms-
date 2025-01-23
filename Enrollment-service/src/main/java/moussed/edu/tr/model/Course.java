package moussed.edu.tr.model;

import java.util.HashSet;
import java.util.Set;

public class Course {

    private Long id;
    private String title;
    private String description;
    private String semester;
    private Set<Long> enrollmentIds = new HashSet<>();

    public Course() {
    }

    public Course(Long id, String title, String description, String semester, Set<Long> enrollmentIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.semester = semester;
        this.enrollmentIds = enrollmentIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Set<Long> getEnrollmentIds() {
        return enrollmentIds;
    }

    public void setEnrollmentIds(Set<Long> enrollmentIds) {
        this.enrollmentIds = enrollmentIds;
    }
}