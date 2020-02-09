package com.neusoftmedical.neumiva.dicompro.ui;

import com.neusoftmedical.neumiva.dicompro.listener.DeleteCheckBoxListener;
import com.neusoftmedical.neumiva.dicompro.listener.ProcessFileListener;
import com.neusoftmedical.neumiva.dicompro.listener.SelectFileListener;

import javax.swing.*;

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
    private JTextField patientName;
    private JCheckBox delPatientName;
    private JTextField hospitalName;
    private JCheckBox delHospitalName;
    private JTextField hospitalAddress;
    private JCheckBox delHospitalAddress;


    public FileProcessForm(){}


    public void FileProcess() {
        JFrame frame = new JFrame("DICOM文件批量脱敏工具");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   //最大化
        frame.pack();
        frame.setVisible(true);

        selectPath.addActionListener(new SelectFileListener(this));
        startButton.addActionListener(new ProcessFileListener(this));
        delPatientName.addItemListener(new DeleteCheckBoxListener(this, 1));
        delHospitalName.addItemListener(new DeleteCheckBoxListener(this,2));
        delHospitalAddress.addItemListener(new DeleteCheckBoxListener(this,3));
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

    public JTextField getPatientName() {
        return patientName;
    }

    public JTextField getHospitalName() {
        return hospitalName;
    }

    public JTextField getHospitalAddress() {
        return hospitalAddress;
    }

}
