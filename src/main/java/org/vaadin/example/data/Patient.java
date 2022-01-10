package org.vaadin.example.data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Table(name = "patients")
//@Entity
public class Patient {

    private Long id;

    //private Long id;
    private String familyname="";
    private String givenname="";
    private String admissiondate="";

    public Patient(String familyname, String givenname, String admissiondate){
        this.familyname = familyname;
        this.givenname = givenname;
        this.admissiondate = admissiondate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getfName() {return familyname;}
    public void setfName(String familyName) {
        this.familyname = familyName;
    }

    public String getgName() {return givenname;}
    public void setgName(String givenName) {
        this.givenname = givenName;
    }

    public void setDate(String admissionDate) {
        this.admissiondate = admissionDate;
    }
    public String getDate(){return admissiondate;}



}
