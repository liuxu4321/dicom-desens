package com.neusoftmedical.neumiva.dicompro.ui;

import com.neusoftmedical.neumiva.dicompro.listener.DicomDesenInfoListener;
import com.neusoftmedical.neumiva.dicompro.listener.ProcessFileListener;
import com.neusoftmedical.neumiva.dicompro.listener.SelectFileListener;
import com.neusoftmedical.neumiva.dicompro.listener.TargetFileListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Jeffer on 2020/2/7.
 */
public class FileProcessForm extends JFrame {
    private JTextField filePath;
    private JButton selectPath;
    private JButton startButton;
    private JLabel fileCount;
    private JLabel processCount;
    private JLabel overCount;
    private JPanel panel;
    private JLabel fileCountValue;
    private JLabel fileProcessValue;
    private JLabel fileOverValue;
    private JTextArea logText;
    private JTextField targetPath;
    private JCheckBox coverFlagCheckBox;
    private JButton targetPathButton;
    private JRadioButton patientNameR1;
    private JRadioButton patientNameR2;
    private JRadioButton patientBirthDateR1;
    private JRadioButton patientBirthDateR2;
    private JRadioButton patientSexR1;
    private JRadioButton patientSexR2;
    private JRadioButton patientAgeR1;
    private JRadioButton patientAgeR2;
    private JRadioButton institutionNameR1;
    private JRadioButton institutionNameR2;
    private JRadioButton institutionAddressR1;
    private JRadioButton institutionAddressR2;
    private JRadioButton operatorNameR1;
    private JRadioButton operatorNameR2;
    private JRadioButton studyIDR1;
    private JRadioButton studyIDR2;
    private JRadioButton accessionNumberR1;
    private JRadioButton accessionNumberR2;


    public FileProcessForm(){}


    public void FileProcess() {
        JFrame frame = new JFrame("DICOM文件批量脱敏工具");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   //最大化
        frame.pack();
        frame.setVisible(true);


        selectPath.addActionListener(new SelectFileListener(this));

        targetPathButton.addActionListener(new TargetFileListener(this));

        coverFlagCheckBox.addItemListener(e -> {
            if(coverFlagCheckBox.isSelected()) {
                this.targetPath.setText("");
                this.targetPath.setEnabled(false);
            }else{
                this.targetPath.setText("");
                this.targetPath.setEnabled(true);
            }
        });


        patientNameR1.addItemListener(new DicomDesenInfoListener(this));
        patientNameR2.addItemListener(new DicomDesenInfoListener(this));
        patientBirthDateR1.addItemListener(new DicomDesenInfoListener(this));
        patientBirthDateR2.addItemListener(new DicomDesenInfoListener(this));
        patientSexR1.addItemListener(new DicomDesenInfoListener(this));
        patientSexR2.addItemListener(new DicomDesenInfoListener(this));
        patientAgeR1.addItemListener(new DicomDesenInfoListener(this));
        patientAgeR2.addItemListener(new DicomDesenInfoListener(this));
        institutionNameR1.addItemListener(new DicomDesenInfoListener(this));
        institutionNameR2.addItemListener(new DicomDesenInfoListener(this));
        institutionAddressR1.addItemListener(new DicomDesenInfoListener(this));
        institutionAddressR2.addItemListener(new DicomDesenInfoListener(this));
        operatorNameR1.addItemListener(new DicomDesenInfoListener(this));
        operatorNameR2.addItemListener(new DicomDesenInfoListener(this));
        studyIDR1.addItemListener(new DicomDesenInfoListener(this));
        studyIDR2.addItemListener(new DicomDesenInfoListener(this));
        accessionNumberR1.addItemListener(new DicomDesenInfoListener(this));
        accessionNumberR2.addItemListener(new DicomDesenInfoListener(this));


        startButton.addActionListener(new ProcessFileListener(this));

    }

    public JTextField getFilePath() {
        return filePath;
    }

    public JLabel getFileCountValue() {
        return fileCountValue;
    }

    public JLabel getFileProcessValue() {
        return fileProcessValue;
    }

    public JLabel getFileOverValue() {
        return fileOverValue;
    }

    public JTextArea getLogText() {
        return logText;
    }


    public JPanel getPanel() {
        return panel;
    }

    public JButton getSelectPath() {
        return selectPath;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JLabel getFileCount() {
        return fileCount;
    }

    public JLabel getProcessCount() {
        return processCount;
    }

    public JLabel getOverCount() {
        return overCount;
    }

    public JTextField getTargetPath() {
        return targetPath;
    }

    public JCheckBox getCoverFlagCheckBox() {
        return coverFlagCheckBox;
    }

    public JButton getTargetPathButton() {
        return targetPathButton;
    }

    public JRadioButton getPatientNameR1() {
        return patientNameR1;
    }

    public JRadioButton getPatientNameR2() {
        return patientNameR2;
    }

    public JRadioButton getPatientBirthDateR1() {
        return patientBirthDateR1;
    }

    public JRadioButton getPatientBirthDateR2() {
        return patientBirthDateR2;
    }

    public JRadioButton getPatientSexR1() {
        return patientSexR1;
    }

    public JRadioButton getPatientSexR2() {
        return patientSexR2;
    }

    public JRadioButton getPatientAgeR1() {
        return patientAgeR1;
    }

    public JRadioButton getPatientAgeR2() {
        return patientAgeR2;
    }

    public JRadioButton getInstitutionNameR1() {
        return institutionNameR1;
    }

    public JRadioButton getInstitutionNameR2() {
        return institutionNameR2;
    }

    public JRadioButton getInstitutionAddressR1() {
        return institutionAddressR1;
    }

    public JRadioButton getInstitutionAddressR2() {
        return institutionAddressR2;
    }

    public JRadioButton getOperatorNameR1() {
        return operatorNameR1;
    }

    public JRadioButton getOperatorNameR2() {
        return operatorNameR2;
    }

    public JRadioButton getStudyIDR1() {
        return studyIDR1;
    }

    public JRadioButton getStudyIDR2() {
        return studyIDR2;
    }

    public JRadioButton getAccessionNumberR1() {
        return accessionNumberR1;
    }

    public JRadioButton getAccessionNumberR2() {
        return accessionNumberR2;
    }
}
