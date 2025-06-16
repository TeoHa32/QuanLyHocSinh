/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class teacher {
    private int id;
    private String fullname;
    private boolean gender;
    private Date DateOfBirth;
    private String phone;
    private String email;
    private String Specialization;

    public teacher() {
    }

    public teacher(int id, String fullname, boolean gender, Date DateOfBirth, String phone, String email, String Specialization) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
        this.phone = phone;
        this.email = email;
        this.Specialization = Specialization;
    }

    public teacher(String fullname, boolean gender, Date DateOfBirth, String phone, String email, String Specialization) {
        this.fullname = fullname;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
        this.phone = phone;
        this.email = email;
        this.Specialization = Specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }
    
}
