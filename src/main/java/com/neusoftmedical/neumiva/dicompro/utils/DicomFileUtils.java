package com.neusoftmedical.neumiva.dicompro.utils;

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

    private static final String PATIENT_NAME = "neusoftmedical";
    private static final String HOSPATIAL_NAME = "neusoftmedical";
    private static final String HOSPATIAL_ADDRESS = "neusoftmedical";

    public static String dicomDesensitize(String filePath, String patientName, String hospitalName, String hospitalAddress) {
        String result = "";
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

            //患者信息
            dcmObj.remove(Tag.PatientName);
            //医院信息
            dcmObj.remove(Tag.InstitutionName);
            dcmObj.remove(Tag.InstitutionAddress);

            if(!patientName.equals("delete")){
                dcmObj.putString(Tag.PatientName, VR.PN, patientName);
            }
            if(!hospitalName.equals("delete")){
                dcmObj.putString(Tag.InstitutionName, VR.LO, hospitalName);
            }
            if(!hospitalAddress.equals("delete")){
                dcmObj.putString(Tag.InstitutionAddress, VR.ST, hospitalAddress);
            }

            din.close();

            FileOutputStream fos;

            fos = new FileOutputStream(srcFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DicomOutputStream dos = new DicomOutputStream(bos);
            dos.writeDicomFile(dcmObj);
            dos.close();
            bos.close();
            fos.close();
            result = "OK";
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
