package org.vaadin.example.patients;


public class PatientInfo{

    private Long patientid;
    private Integer id;
    private String patientname;
    private String roomnum;
    private String admissiondate;
    private String condition;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientid;
    }
    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }


    public String getName() {return patientname;}
    public void setName(String patientname) {
        this.patientname = patientname;
    }

    public String getroom() {return roomnum;}
    public void setroom(String roomnum) {
        this.roomnum = roomnum;
    }

    public void setDate(String admissionDate) {
        this.admissiondate = admissionDate;
    }
    public String getDate(){return admissiondate;}

    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition(){return condition;}



}
