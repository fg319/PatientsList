package org.vaadin.example.data;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "patients")
public class Patient {

    private Long id;
    private String familyname;
    private String givenname;
    private String admissiondate;

    public String getfName() {
        return familyname;
    }
    public void setfName(String familyname) {
        this.familyname = familyname;
    }

    public String getgName() {return givenname;}
    public void setgName(String givenname) {
        this.givenname = givenname;
    }

    public void setDate(String admissiondate) {
        this.admissiondate = admissiondate;
    }
    public String getDate(){return admissiondate;}

}
