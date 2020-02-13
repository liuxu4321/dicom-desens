package com.neusoftmedical.neumiva.dicompro.beans;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class DicomHospitalInfo {

    private String institutionName;
    private String institutionAddress;

    public DicomHospitalInfo(String institutionName, String institutionAddress) {
        this.institutionName = institutionName;
        this.institutionAddress = institutionAddress;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }
}
