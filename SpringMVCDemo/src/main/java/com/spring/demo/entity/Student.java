package com.spring.demo.entity;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String programmingLanguage;
    private String[] operatingSystems;
    private Map<String, String> countries;

    public Student() {
        initCountries();
    }

    public Student(String firstName, String lastName, String country, String programmingLanguage, String[] operatingSystems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.programmingLanguage = programmingLanguage;
        this.operatingSystems = operatingSystems;
        initCountries();
    }

    private void initCountries() {
        countries = new LinkedHashMap<>();
        countries.put("US", "USA");
        countries.put("RU", "Russia");
        countries.put("PL", "Poland");
        countries.put("GER", "Germany");
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String[] getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(String[] operatingSystems) {
        this.operatingSystems = operatingSystems;
    }
}
