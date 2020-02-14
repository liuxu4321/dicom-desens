package com.neusoftmedical.neumiva.dicompro.listener;

import com.neusoftmedical.neumiva.dicompro.ui.FileProcessForm;
import com.neusoftmedical.neumiva.dicompro.ui.VerifyDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jeffer on 2020/2/14.
 */
public class VerifyDicomListener implements ActionListener {

    private JFileChooser fileChooser = new JFileChooser();

    private FileProcessForm fileProcessForm;

    public VerifyDicomListener(FileProcessForm fileProcessForm) {
        this.fileProcessForm = fileProcessForm;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        VerifyDialog dialog = new VerifyDialog();
        dialog.pack();
        dialog.setSize(600,400);
        dialog.setTitle("DICOM文件信息验证");
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


    }
}
