package com.neusoftmedical.neumiva.dicompro.beans;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class DicomPatientInfo {

    private String patientName;
    private String patientBirthDate;
    private String patientSex;
    private String patientAge;

    public DicomPatientInfo() {
    }

    public DicomPatientInfo(String patientName, String patientBirthDate, String patientSex, String patientAge) {
        this.patientName = patientName;
        this.patientBirthDate = patientBirthDate;
        this.patientSex = patientSex;
        this.patientAge = patientAge;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(String patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }
}
