package org.vaadin.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.vaadin.example.data.Patient;
import org.vaadin.example.repository.PatientRepository;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    List<Patient> patients;
    Patient patient;

    public PatientService() throws Exception {
        List<String> givenname = GetGName();
        List<String> familyname = GetFName();
        List<String> admissiondate =GetDate();

        for(int i = 0; i < givenname.size(); i++){
            patient.setgName(givenname.get(i));
            patient.setfName(familyname.get(i));
            patient.setDate(admissiondate.get(i));
            patients.add(patient);
        }
    }


    public ResultSet getData(String query) throws Exception{
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(dbUrl, "postgres", "userpass");
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public List<String> GetFName() throws Exception {
        ArrayList<String> FirstName = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            FirstName.add(rs.getString("familyname"));
        }
        return FirstName;
    }

    public List<String> GetGName() throws Exception {
        ArrayList<String> GivenName = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            GivenName.add(rs.getString("givenname"));
        }
        return GivenName;
    }

    public List<String> GetDate() throws Exception {
        ArrayList<String> AdmissionDate = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            AdmissionDate.add(rs.getString("admissiondate"));
        }
        return AdmissionDate;
    }

}
