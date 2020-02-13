package com.neusoftmedical.neumiva.dicompro.listener;

import com.neusoftmedical.neumiva.dicompro.beans.DicomOptionInfo;
import com.neusoftmedical.neumiva.dicompro.beans.DicomPatientInfo;
import com.neusoftmedical.neumiva.dicompro.ui.FileProcessForm;
import com.neusoftmedical.neumiva.dicompro.utils.ApplicationContext;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class DicomDesenInfoListener implements ItemListener {

    private FileProcessForm fileProcessForm;

    public DicomDesenInfoListener(FileProcessForm fileProcessForm) {
        this.fileProcessForm = fileProcessForm;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        DicomOptionInfo dicomOptionInfo = new DicomOptionInfo();
        if(fileProcessForm.getPatientNameR2().isSelected()) {
            dicomOptionInfo.setPatientNameOption(1);
        }

        if(fileProcessForm.getPatientAgeR2().isSelected()) {
            dicomOptionInfo.setPatientAgeOption(1);
        }

        if(fileProcessForm.getPatientBirthDateR2().isSelected()) {
            dicomOptionInfo.setPatientBirthDateOption(1);
        }

        if(fileProcessForm.getPatientSexR2().isSelected()) {
            dicomOptionInfo.setPatientSexOption(1);
        }

        if(fileProcessForm.getInstitutionNameR2().isSelected()) {
            dicomOptionInfo.setInstitutionNameOption(1);
        }

        if(fileProcessForm.getInstitutionAddressR2().isSelected()) {
            dicomOptionInfo.setInstitutionAddressOption(1);
        }

        if(fileProcessForm.getStudyIDR2().isSelected()) {
            dicomOptionInfo.setStudyIDOption(1);
        }

        if(fileProcessForm.getOperatorNameR2().isSelected()) {
            dicomOptionInfo.setOperatorNameOption(1);
        }

        if(fileProcessForm.getAccessionNumberR2().isSelected()) {
            dicomOptionInfo.setAccessionNumberOption(1);
        }


        ApplicationContext.register("DicomOptionInfo" , dicomOptionInfo);

    }
}
