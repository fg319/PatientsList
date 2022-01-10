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
import java.util.*;

@Service
public class PatientService {

    List<Patient> patients;
    Patient patient;

    public PatientService() throws Exception {
        Patient patient= new Patient("","","");
        GetList(patient);
    }

    public List<Patient> GetList(Patient patient) throws Exception {
        this.patient=patient;
        ArrayList<Patient>patients1= new ArrayList<>();
        List<String> givenName = GetGName();
        List<String> familyName = GetFName();
        List<String> admissionDate =GetDate();

        for(int i = 0; i < givenName.size(); i++){
            patient.setgName(givenName.get(i));
            patient.setfName(familyName.get(i));
            patient.setDate(admissionDate.get(i));
            patients1.add(patient);
        }
        return patients1;
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
            if(rs.getString("familyname") == null){
                FirstName.add("not working");
            }
        }
        return FirstName;
    }

    public List<String> GetGName() throws Exception {
        ArrayList<String> GivenName = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            GivenName.add(rs.getString("givenname"));
            if(rs.getString("givenname") == null){
                GivenName.add("not working");
            }
        }
        return GivenName;
    }

    public List<String> GetDate() throws Exception {
        ArrayList<String> AdmissionDate = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            AdmissionDate.add(rs.getString("admissiondate"));
            if(rs.getString("AdmissionDate") == null){
                AdmissionDate.add("not working");
            }
        }
        return AdmissionDate;
    }
}
