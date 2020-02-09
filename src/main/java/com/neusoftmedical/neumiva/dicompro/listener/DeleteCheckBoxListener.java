package com.neusoftmedical.neumiva.dicompro.listener;

import com.neusoftmedical.neumiva.dicompro.ui.FileProcessForm;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Jeffer on 2020/2/8.
 */
public class DeleteCheckBoxListener implements ItemListener {

    private FileProcessForm fileProcessForm;

    //1:patientName
    //2:hospitalName
    //3:hospitalAddress
    private int checkId;

    public DeleteCheckBoxListener(FileProcessForm fileProcessForm, int checkId) {
        this.fileProcessForm = fileProcessForm;
        this.checkId = checkId;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {

        JCheckBox jcb = (JCheckBox) e.getItem();

        if (jcb.isSelected()) {
            if(checkId == 1) {
                fileProcessForm.getPatientName().setText("delete");
                fileProcessForm.getPatientName().setEnabled(false);
            } else if(checkId == 2) {
                fileProcessForm.getHospitalName().setText("delete");
                fileProcessForm.getHospitalName().setEnabled(false);
            } else if(checkId == 3) {
                fileProcessForm.getHospitalAddress().setText("delete");
                fileProcessForm.getHospitalAddress().setEnabled(false);
            }
        } else {
            if(checkId == 1) {
                fileProcessForm.getPatientName().setText("");
                fileProcessForm.getPatientName().setEnabled(true);
            } else if(checkId == 2) {
                fileProcessForm.getHospitalName().setText("");
                fileProcessForm.getHospitalName().setEnabled(true);
            } else if(checkId == 3) {
                fileProcessForm.getHospitalAddress().setText("");
                fileProcessForm.getHospitalAddress().setEnabled(true);
            }
        }

    }
}
