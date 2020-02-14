package com.neusoftmedical.neumiva.dicompro.utils;

import com.neusoftmedical.neumiva.dicompro.beans.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.util.SafeClose;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * Created by Jeffer on 2020/2/7.
 */
public class DicomFileUtils {

    private static final String STRING_VALUE = "**";
    private static final String INTEGER_VALUE = "0";
    private static final String DATE_VALUE = "00000000";

    private static final String TEMP_FOLDER  = System.getProperty("java.io.tmpdir");
    private static final ImageReader imageReader = ImageIO.getImageReadersByFormatName("DICOM").next();

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

        Attributes dcmObj;
        Attributes fmi;
        DicomInputStream din;

        try {
            din = new DicomInputStream(srcFile);

            dcmObj = din.readDataset(-1, -1);
            fmi = din.readFileMetaInformation();

            result = dcmObj.getString(Tag.PatientName);

            //0 匿名化， //1 删除
            if(dicomOptionInfo.getPatientNameOption() == 0) {
                dcmObj.setString(Tag.PatientName, VR.PN, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.PatientName);
            }

            if(dicomOptionInfo.getPatientAgeOption() == 0) {
                dcmObj.setString(Tag.PatientAge, VR.AS, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.PatientAge);
            }

            if(dicomOptionInfo.getPatientBirthDateOption() == 0) {
                dcmObj.setString(Tag.PatientBirthDate, VR.DA, DATE_VALUE);
            }else{
                dcmObj.remove(Tag.PatientBirthDate);
            }

            if(dicomOptionInfo.getPatientSexOption() == 0) {
                dcmObj.setString(Tag.PatientSex, VR.CS, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.PatientSex);
            }

            if(dicomOptionInfo.getInstitutionNameOption() == 0) {
                dcmObj.setString(Tag.InstitutionName, VR.LO, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.InstitutionName);
            }

            if(dicomOptionInfo.getInstitutionAddressOption() == 0) {
                dcmObj.setString(Tag.InstitutionAddress, VR.ST, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.InstitutionAddress);
            }

            if(dicomOptionInfo.getStudyIDOption() == 0) {
                dcmObj.setString(Tag.StudyID, VR.SH, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.StudyID);
            }

            if(dicomOptionInfo.getAccessionNumberOption() == 0) {
                dcmObj.setString(Tag.AccessionNumber, VR.SH, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.AccessionNumber);
            }

            if(dicomOptionInfo.getOperatorNameOption() == 0) {
                dcmObj.setString(Tag.OperatorsName, VR.PN, STRING_VALUE);
            }else{
                dcmObj.remove(Tag.OperatorsName);
            }

            din.close();

            File targetFile = null;
            if(folderOptionInfo.getCoverFlag() == 0){
                targetPath = filePath.replace(folderOptionInfo.getSourceFolder(), folderOptionInfo.getTargetFolder());
                File file = new File(targetPath);
                if(!file.exists()) {
                    FileUtils.touch(new File(targetPath));
                }
                targetFile = new File(targetPath);
            }else {
                targetPath = srcFile.getAbsolutePath();
                targetFile = srcFile;
            }

            DicomOutputStream dos = new DicomOutputStream(targetFile);
            dos.writeDataset(fmi, dcmObj);
            dos.finish();
            dos.flush();
            dos.close();
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
        Attributes  dcmObj;
        DicomInputStream din;

        try {
            din = new DicomInputStream(srcFile);
            dcmObj = din.readDataset(-1, Tag.PixelData);

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

    public static DicomPatientInfo getPatientInfo(final Attributes dcmObj) {
        DicomPatientInfo dicomPatientInfo = new DicomPatientInfo();
        dicomPatientInfo.setPatientName(dcmObj.getString(Tag.PatientName));
        dicomPatientInfo.setPatientAge(dcmObj.getString(Tag.PatientAge));
        dicomPatientInfo.setPatientBirthDate(dcmObj.getString(Tag.PatientBirthDate));
        dicomPatientInfo.setPatientSex(dcmObj.getString(Tag.PatientSex));
        return dicomPatientInfo;
    }

    public static DicomHospitalInfo getHospitalInfo(final Attributes dcmObj) {
        DicomHospitalInfo dicomHospitalInfo = new DicomHospitalInfo();
        dicomHospitalInfo.setInstitutionName(dcmObj.getString(Tag.InstitutionName));
        dicomHospitalInfo.setInstitutionAddress(dcmObj.getString(Tag.InstitutionAddress));
        return dicomHospitalInfo;
    }

    public static DicomStudyInfo getStudyInfo(final Attributes dcmObj) {
        DicomStudyInfo studyInfo = new DicomStudyInfo();
        studyInfo.setStudyID(dcmObj.getString(Tag.StudyID));
        studyInfo.setAccessionNumber(dcmObj.getString(Tag.AccessionNumber));
        studyInfo.setOperatorName(dcmObj.getString(Tag.OperatorsName));
        return studyInfo;
    }


    public static File dicom2JPEG(String filePath) throws IOException {
        return dicom2JPEG(new File(filePath));
    }



    public static File dicom2JPEG(File srcDicomFile) throws IOException {
        File destJPEGFile = new File(TEMP_FOLDER + "/tempdicomimage.jpg");
        Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
        ImageReader reader = iter.next();
        ImageInputStream iis = ImageIO.createImageInputStream(srcDicomFile);
        BufferedImage bi;
        OutputStream out = null;
        try{
            reader.setInput(iis, false);
            bi = readImage(iis);
            if (bi == null) {
                return null;
            }
            out = new BufferedOutputStream(new FileOutputStream(destJPEGFile));
            JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(out);  //这里也可以使用流将图像导出到web应用，用来搭建web版的PACS等。
            enc.encode(bi);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            SafeClose.close(iis);
            SafeClose.close(out);
        }
        return destJPEGFile;

    }
    private static float windowCenter;
    private static float windowWidth;
    private static boolean autoWindowing = true;
    private static int windowIndex;
    private static int voiLUTIndex;
    private static boolean preferWindow = true;
    private static Attributes prState;
    private static int overlayActivationMask = 0xffff;
    private static int overlayGrayscaleValue = 0xffff;
    private static int frame = 1;

    private static BufferedImage readImage(ImageInputStream iis) throws IOException{
        imageReader.setInput(iis);
        return imageReader.read(frame -1, readParam());
    }

    private static ImageReadParam readParam(){
        DicomImageReadParam param = (DicomImageReadParam) imageReader.getDefaultReadParam();
        /*param.setWindowCenter(windowCenter);
        param.setWindowWidth(windowWidth);
        param.setAutoWindowing(autoWindowing);
        param.setWindowIndex(windowIndex);
        param.setVOILUTIndex(voiLUTIndex);
        param.setPreferWindow(preferWindow);
        param.setPresentationState(prState);
        param.setOverlayActivationMask(overlayActivationMask);
        param.setOverlayGrayscaleValue(overlayGrayscaleValue);*/
        return param;
    }



    public static void main(String[] args) {
        String str = "/Users/liuxu/Documents/DICOM/问题DICOM/CT1/18DC60ABD393FCBD1346806C8D4A007D/CT.1.2.156.14702.1.1002.16.2.20190623193611164213";

        //dicomDesensitize(str);


        //test(str);



    }
}
