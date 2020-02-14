package com.neusoftmedical.neumiva.dicompro.utils;

import com.neusoftmedical.neumiva.dicompro.beans.*;
import org.apache.commons.io.FileUtils;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.data.VR;
import org.dcm4che2.io.DicomInputStream;
import org.dcm4che2.io.DicomOutputStream;

import java.io.*;

/**
 * Created by Jeffer on 2020/2/7.
 */
public class DicomFileUtils {

    private static final String STRING_VALUE = "**";
    private static final String INTEGER_VALUE = "0";
    private static final String DATE_VALUE = "00000000";

    public static String dicomDesensitize(String filePath) {
        FolderOptionInfo folderOptionInfo = (FolderOptionInfo)ApplicationContext.getBean("FolderOptionInfo");
        DicomOptionInfo dicomOptionInfo = (DicomOptionInfo)ApplicationContext.getBean("DicomOptionInfo");
        if(dicomOptionInfo == null) {
            dicomOptionInfo = new DicomOptionInfo();
        }
        String result = "";
        String targetPath = "";

        File srcFile = new File(filePath);
        if(!isDicom(srcFile)){
            return " not dicom ";
        }

        DicomObject dcmObj;
        DicomInputStream din;

        try {
            din = new DicomInputStream(srcFile);
            dcmObj = din.readDicomObject();
            result = dcmObj.getString(Tag.PatientName);

            //0 匿名化， //1 删除
            if(dicomOptionInfo.getPatientNameOption() == 0) {
                dcmObj.putString(Tag.PatientName, VR.PN, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.PatientName);
            }

            if(dicomOptionInfo.getPatientAgeOption() == 0) {
                dcmObj.putString(Tag.PatientAge, VR.AS, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.PatientAge);
            }

            if(dicomOptionInfo.getPatientBirthDateOption() == 0) {
                dcmObj.putString(Tag.PatientBirthDate, VR.DA, DATE_VALUE);
            }else{
                dcmObj.remove(Tag.PatientBirthDate);
            }

            if(dicomOptionInfo.getPatientSexOption() == 0) {
                dcmObj.putString(Tag.PatientSex, VR.CS, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.PatientSex);
            }

            if(dicomOptionInfo.getInstitutionNameOption() == 0) {
                dcmObj.putString(Tag.InstitutionName, VR.LO, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.InstitutionName);
            }

            if(dicomOptionInfo.getInstitutionAddressOption() == 0) {
                dcmObj.putString(Tag.InstitutionAddress, VR.ST, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.InstitutionAddress);
            }

            if(dicomOptionInfo.getStudyIDOption() == 0) {
                dcmObj.putString(Tag.StudyID, VR.SH, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.StudyID);
            }

            if(dicomOptionInfo.getAccessionNumberOption() == 0) {
                dcmObj.putString(Tag.AccessionNumber, VR.SH, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.AccessionNumber);
            }

            if(dicomOptionInfo.getOperatorNameOption() == 0) {
                dcmObj.putString(Tag.OperatorsName, VR.PN, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.OperatorsName);
            }





            din.close();



            FileOutputStream fos;

            if(folderOptionInfo.getCoverFlag() == 0){
                targetPath = filePath.replace(folderOptionInfo.getSourceFolder(), folderOptionInfo.getTargetFolder());
                File file = new File(targetPath);
                if(!file.exists()) {
                    FileUtils.touch(new File(targetPath));
                }
                fos = new FileOutputStream(new File(targetPath));
            }else {
                targetPath = srcFile.getAbsolutePath();
                fos = new FileOutputStream(srcFile);
            }

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DicomOutputStream dos = new DicomOutputStream(bos);
            dos.writeDicomFile(dcmObj);
            dos.close();
            bos.close();
            fos.close();
            result = targetPath + " OK";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isDicom(File file) {

        if (FileUtils.sizeOf(file) == 0)
            return false;
        byte[] data = null;
        try {
            data = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data.length < 128) {
            return false;
        }
        int b0 = data[0] & 255, b1 = data[1] & 255, b2 = data[2] & 255, b3 = data[3] & 255;
        if (data[128] == 68 && data[129] == 73 && data[130] == 67 && data[131] == 77)
            return true;
        else if ((b0 == 8 || b0 == 2) && b1 == 0 && b3 == 0)
            return true;
        return false;
    }

    public static DicomInfo getDicomTagValue(String filePath) {
        File srcFile = new File(filePath);
        if(!srcFile.exists() || srcFile.isDirectory() || !isDicom(srcFile)) {
            return null;
        }
        DicomInfo dicomInfo = new DicomInfo();
        DicomObject dcmObj;
        DicomInputStream din;

        try {
            din = new DicomInputStream(srcFile);
            dcmObj = din.readDicomObject();

            DicomPatientInfo dicomPatientInfo = getPatientInfo(dcmObj);
            DicomHospitalInfo dicomHospitalInfo = getHospitalInfo(dcmObj);
            DicomStudyInfo dicomStudyInfo = getStudyInfo(dcmObj);
            dicomInfo.setDicomPatientInfo(dicomPatientInfo);
            dicomInfo.setDicomHospitalInfo(dicomHospitalInfo);
            dicomInfo.setDicomStudyInfo(dicomStudyInfo);
            return dicomInfo;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DicomPatientInfo getPatientInfo(final DicomObject dcmObj) {
        DicomPatientInfo dicomPatientInfo = new DicomPatientInfo();
        dicomPatientInfo.setPatientName(dcmObj.getString(Tag.PatientName));
        dicomPatientInfo.setPatientAge(dcmObj.getString(Tag.PatientAge));
        dicomPatientInfo.setPatientBirthDate(dcmObj.getString(Tag.PatientBirthDate));
        dicomPatientInfo.setPatientSex(dcmObj.getString(Tag.PatientSex));
        return dicomPatientInfo;
    }

    public static DicomHospitalInfo getHospitalInfo(final DicomObject dcmObj) {
        DicomHospitalInfo dicomHospitalInfo = new DicomHospitalInfo();
        dicomHospitalInfo.setInstitutionName(dcmObj.getString(Tag.InstitutionName));
        dicomHospitalInfo.setInstitutionAddress(dcmObj.getString(Tag.InstitutionAddress));
        return dicomHospitalInfo;
    }

    public static DicomStudyInfo getStudyInfo(final DicomObject dcmObj) {
        DicomStudyInfo studyInfo = new DicomStudyInfo();
        studyInfo.setStudyID(dcmObj.getString(Tag.StudyID));
        studyInfo.setAccessionNumber(dcmObj.getString(Tag.AccessionNumber));
        studyInfo.setOperatorName(dcmObj.getString(Tag.OperatorsName));
        return studyInfo;
    }

    public static void test(String filePath) {
        File srcFile = new File(filePath);
        DicomObject dcmObj;
        DicomInputStream din;

        try {
            din = new DicomInputStream(srcFile);
            dcmObj = din.readDicomObject();

            System.out.println(dcmObj.getString(Tag.PatientName));
            System.out.println(dcmObj.getString(Tag.InstitutionName));
            System.out.println(dcmObj.getString(Tag.InstitutionAddress));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String str = "/Users/liuxu/Documents/DICOM/问题DICOM/CT1/18DC60ABD393FCBD1346806C8D4A007D/CT.1.2.156.14702.1.1002.16.2.20190623193611164213";

        //dicomDesensitize(str);


        //test(str);



    }
}
