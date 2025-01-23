package moussed.edu.tr.model;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String department;
    private String imageFile;
    private String role;
    @ElementCollection
    private Set<Long> enrollmentIds = new HashSet<>();

    public Person() {
    }

    public Person(Integer id, String userName, String firstName, String lastName,String email, String password, String role, String imageFile, String department, Set<Long> enrollmentIds) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.imageFile = imageFile;
        this.department = department;
        this.enrollmentIds = enrollmentIds;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Long> getEnrollmentIds() {
        return enrollmentIds;
    }

    public void setEnrollmentIds(Set<Long> enrollmentIds) {
        this.enrollmentIds = enrollmentIds;
    }

}
