package fi.metropolia.juhanaha.database_solutions_project.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private CustomerProfileSimpleDto profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerProfileSimpleDto getProfile() {
        return profile;
    }

    public void setProfile(CustomerProfileSimpleDto profile) {
        this.profile = profile;
    }

}
