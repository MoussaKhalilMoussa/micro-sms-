package moussed.edu.tr.model.dto;

import java.time.LocalDate;

public class EnrollmentDto {

    private int tuitionFee;
    private String state ;
    private LocalDate classDate;
    private Integer personId;
    private Long courseId;

    public EnrollmentDto() {
    }

    public EnrollmentDto(int tuitionFee, String state, LocalDate classDate, Integer personId, Long courseId) {
        this.tuitionFee = tuitionFee;
        this.state = state;
        this.classDate = classDate;
        this.personId = personId;
        this.courseId = courseId;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getClassDate() {
        return classDate;
    }

    public void setClassDate(LocalDate classDate) {
        this.classDate = classDate;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
