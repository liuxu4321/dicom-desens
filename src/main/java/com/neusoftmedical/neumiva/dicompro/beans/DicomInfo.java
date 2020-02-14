package com.neusoftmedical.neumiva.dicompro.beans;

/**
 * Created by Jeffer on 2020/2/14.
 */
public class DicomInfo {
    private DicomPatientInfo dicomPatientInfo;
    private DicomHospitalInfo dicomHospitalInfo;
    private DicomStudyInfo dicomStudyInfo;

    public DicomInfo() {
    }

    public DicomPatientInfo getDicomPatientInfo() {
        return dicomPatientInfo;
    }

    public void setDicomPatientInfo(DicomPatientInfo dicomPatientInfo) {
        this.dicomPatientInfo = dicomPatientInfo;
    }

    public DicomHospitalInfo getDicomHospitalInfo() {
        return dicomHospitalInfo;
    }

    public void setDicomHospitalInfo(DicomHospitalInfo dicomHospitalInfo) {
        this.dicomHospitalInfo = dicomHospitalInfo;
    }

    public DicomStudyInfo getDicomStudyInfo() {
        return dicomStudyInfo;
    }

    public void setDicomStudyInfo(DicomStudyInfo dicomStudyInfo) {
        this.dicomStudyInfo = dicomStudyInfo;
    }
}
