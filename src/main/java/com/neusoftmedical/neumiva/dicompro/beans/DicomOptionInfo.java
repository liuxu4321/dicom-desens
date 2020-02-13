package com.neusoftmedical.neumiva.dicompro.beans;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class DicomOptionInfo {

    private int patientNameOption = 0;
    private int patientBirthDateOption = 0;
    private int patientSexOption = 0;
    private int patientAgeOption = 0;


    private int institutionNameOption = 0;
    private int institutionAddressOption = 0;

    private int studyIDOption = 0;
    private int accessionNumberOption = 0;
    private int operatorNameOption = 0;

    public DicomOptionInfo() {
    }


    public int getPatientNameOption() {
        return patientNameOption;
    }

    public void setPatientNameOption(int patientNameOption) {
        this.patientNameOption = patientNameOption;
    }

    public int getPatientBirthDateOption() {
        return patientBirthDateOption;
    }

    public void setPatientBirthDateOption(int patientBirthDateOption) {
        this.patientBirthDateOption = patientBirthDateOption;
    }

    public int getPatientSexOption() {
        return patientSexOption;
    }

    public void setPatientSexOption(int patientSexOption) {
        this.patientSexOption = patientSexOption;
    }

    public int getPatientAgeOption() {
        return patientAgeOption;
    }

    public void setPatientAgeOption(int patientAgeOption) {
        this.patientAgeOption = patientAgeOption;
    }

    public int getInstitutionNameOption() {
        return institutionNameOption;
    }

    public void setInstitutionNameOption(int institutionNameOption) {
        this.institutionNameOption = institutionNameOption;
    }

    public int getInstitutionAddressOption() {
        return institutionAddressOption;
    }

    public void setInstitutionAddressOption(int institutionAddressOption) {
        this.institutionAddressOption = institutionAddressOption;
    }

    public int getStudyIDOption() {
        return studyIDOption;
    }

    public void setStudyIDOption(int studyIDOption) {
        this.studyIDOption = studyIDOption;
    }

    public int getAccessionNumberOption() {
        return accessionNumberOption;
    }

    public void setAccessionNumberOption(int accessionNumberOption) {
        this.accessionNumberOption = accessionNumberOption;
    }

    public int getOperatorNameOption() {
        return operatorNameOption;
    }

    public void setOperatorNameOption(int operatorNameOption) {
        this.operatorNameOption = operatorNameOption;
    }
}
