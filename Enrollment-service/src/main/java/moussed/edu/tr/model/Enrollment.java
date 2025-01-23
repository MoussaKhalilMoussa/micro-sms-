package moussed.edu.tr.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Enrollment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"personId", "courseId"})
})
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate enrollmentDate;

    private int tuitionFee;

    private String state;

    private LocalDate classDate;

    @Column(nullable = false)
    private Integer personId;

    @Column(nullable = false)
    private Long courseId;

    public Enrollment() {
    }

    public Enrollment(Long id, LocalDate enrollmentDate, int tuitionFee, String state, LocalDate classDate, Integer personId, Long courseId) {
        this.id = id;
        this.enrollmentDate = enrollmentDate;
        this.tuitionFee = tuitionFee;
        this.state = state;
        this.classDate = classDate;
        this.personId = personId;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(personId, that.personId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, courseId);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", enrollmentDate=" + enrollmentDate +
                ", tuitionFee=" + tuitionFee +
                ", state='" + state + '\'' +
                ", classDate=" + classDate +
                ", personId=" + personId +
                ", courseId=" + courseId +
                '}';
    }
}
