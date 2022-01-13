package org.vaadin.example.controller;

import org.springframework.stereotype.Service;
import org.vaadin.example.data.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Service
public class PatientService {

    public Patient createP(String PatientName, String room, String admissionDate, Long PatientId, String Condition){
        Patient patient = new Patient();
        patient.setName(PatientName);
        patient.setroom(room);
        patient.setDate(admissionDate);
        patient.setPatientid(PatientId);
        patient.setCondition(Condition);
        return patient;
    }

    public List<Patient> GetList() throws Exception {
        Patient patient;
        ArrayList<Patient>patients1= new ArrayList<>();
        List<String> room = GetRoom();
        List<String> PatientName = GetName();
        List<String> admissionDate =GetDate();
        List<Long> PatientId= GetId();
        List<String> Condition = GetCondition();

        for(int i = 0; i < PatientName.size(); i++){
            patient = createP(PatientName.get(i),room.get(i),admissionDate.get(i), PatientId.get(i),Condition.get(i));
            patients1.add(patient);
        }
        return patients1;
    }

    public List<Patient> search(String searchInput) throws Exception {        // This fucntion was taken from https://www.baeldung.com/find-list-element-java
        List<Patient> patients = GetList();
        ArrayList<Patient> patientUpdated = new ArrayList<>();
        if(searchInput == null || searchInput.isEmpty()){
            return patients;
        }else {
            for (Patient patient : patients){
                if(patient.getName().equals(searchInput)){
                    patientUpdated.add(patient);
                }
            }
            return patientUpdated;
        }

    }

    public ResultSet getData(String query) throws Exception{
        String dbUrl = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/dctpppdsoogu5e";
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(dbUrl, "wtlubuspzbefzf", "6056c0cef2cfcbf15902982f17d7ba4a19158dd1087ecb110fce1aade0e0629b");
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public List<String> GetName() throws Exception {
        ArrayList<String> PatientName = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            PatientName.add(rs.getString("patientname"));

        }
        return PatientName;
    }

    public List<String> GetRoom() throws Exception {
        ArrayList<String> Room = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            Room.add(rs.getString("roomnum"));

        }
        return Room;
    }

    public List<String> GetDate() throws Exception {
        ArrayList<String> AdmissionDate = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            AdmissionDate.add(rs.getString("dateadmitted"));
        }
        return AdmissionDate;
    }

    public List<Long> GetId() throws Exception {
        ArrayList<Long> PatientId = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            PatientId.add(rs.getLong("patientid"));
        }
        return PatientId;
    }

    public List<String> GetCondition() throws Exception {
        ArrayList<String> Condition = new ArrayList();

        ResultSet rs = getData("Select * from patients");

        while(rs.next()) {
            Condition.add(rs.getString("condition"));
        }
        return Condition;
    }
}
