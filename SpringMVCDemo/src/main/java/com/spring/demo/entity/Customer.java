package com.spring.demo.entity;

import com.spring.demo.annotation.CourseCode;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.*;

public class Customer {

    @NotNull(message = "empty data is not allowed")
    @Size(min = 2, message = "length more than 1 is required")
    private String firstName;

    @NotNull(message = "empty data is not allowed")
    @Size(min = 2, message = "length more than 1 is required")
    private String lastName;

    @NotNull(message = "must be from 0 to 10 inclusive")
    @Min(value = 0, message = "must be greater or equal to zero")
    @Max(value = 10, message = "must be less or equal to ten")
    private Integer freePasses;

    @NotNull(message = "only 5 digits/chars")
    @Pattern(regexp = "[a-zA-Z0-9]{5}", message = "only 5 digits/chars")
    private String postalCode;

    @CourseCode
    private String courseCode;

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

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
