package moussed.edu.tr.model;

public class CourseDto {

    private String title;
    private String description;
    private String semester;

    public CourseDto() {
    }

    public CourseDto(String title, String description, String semester) {
        this.title = title;
        this.description = description;
        this.semester = semester;
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

}
