package com.neusoftmedical.neumiva.dicompro.beans;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class DicomStudyInfo {

    private String studyID;
    private String accessionNumber;
    private String operatorName;

    public DicomStudyInfo(String studyID, String accessionNumber, String operatorName) {
        this.studyID = studyID;
        this.accessionNumber = accessionNumber;
        this.operatorName = operatorName;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
