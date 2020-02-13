package com.neusoftmedical.neumiva.dicompro.listener;

import com.neusoftmedical.neumiva.dicompro.ui.FileProcessForm;
import com.neusoftmedical.neumiva.dicompro.utils.CustomFileUtils;
import com.neusoftmedical.neumiva.dicompro.utils.DicomFileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffer on 2020/2/7.
 */
public class ProcessFileListener implements ActionListener {

    private JFileChooser fileChooser = new JFileChooser();

    private FileProcessForm fileProcessForm;

    public ProcessFileListener(FileProcessForm fileProcessForm) {
        this.fileProcessForm = fileProcessForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = fileProcessForm.getFilePath().getText();
        if(StringUtils.isEmpty(path)){
            JOptionPane.showMessageDialog(fileProcessForm.getPanel(), "请选择文件夹", "消息", JOptionPane.DEFAULT_OPTION);
            return;
        }


        int chooseResult = JOptionPane.showConfirmDialog(fileProcessForm.getPanel(), "确认要执行吗？", "确认", JOptionPane.YES_NO_OPTION);
        if(chooseResult != JOptionPane.YES_OPTION){
            return;
        }

        List<File> fileList = new ArrayList<>();
        CustomFileUtils.traverseFolder2(path, fileList);
        JTextArea jTextArea = fileProcessForm.getLogText();
        JLabel processLabel = fileProcessForm.getFileProcessValue();
        JLabel overLabel = fileProcessForm.getFileOverValue();
        String patientName = fileProcessForm.getPatientName().getText();
        String hospitalName = fileProcessForm.getHospitalName().getText();
        String hospitalAddress = fileProcessForm.getHospitalAddress().getText();
        int countValue = Integer.parseInt(fileProcessForm.getFileCountValue().getText());
        int processValue = 0;
        int overValue = 0;
        for(File file : fileList){
            jTextArea.append("正在处理："  + file.getAbsolutePath() + "\r\n");
            String result = DicomFileUtils.dicomDesensitize(file.getAbsolutePath(), patientName, hospitalName, hospitalAddress);
            jTextArea.append("处理完成："  + file.getAbsolutePath() + "  " + result + "\r\n");
            processValue++;
            processLabel.setText(processValue + "");
            overLabel.setText((countValue - processValue) + "");

        }

    }
}