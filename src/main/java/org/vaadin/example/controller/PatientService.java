package org.vaadin.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.vaadin.example.data.Patient;


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


    }

    public List<Patient> GetList() throws Exception {
        Patient patient;
        //this.patient=patient;
        ArrayList<Patient>patients1= new ArrayList<>();
        List<String> givenName = GetGName();
        List<String> familyName = GetFName();
        List<String> admissionDate =GetDate();
        List<Integer> PatientId= GetId();

        for(int i = 0; i < givenName.size(); i++){
            patient = createP(familyName.get(i),givenName.get(i),admissionDate.get(i), PatientId.get(i));
            patients1.add(patient);
        }
        return patients1;
    }

    public Patient createP(String familyName, String givenName, String admissionDate, Integer PatientId){
        Patient patient = new Patient();
        patient.setgName(givenName);
        patient.setfName(familyName);
        patient.setDate(admissionDate);
        patient.setId(PatientId);
        return patient;
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

    public List<Integer> GetId() throws Exception {
        ArrayList<Integer> PatientId = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            PatientId.add(rs.getInt("id"));
        }
        return PatientId;
    }
}
