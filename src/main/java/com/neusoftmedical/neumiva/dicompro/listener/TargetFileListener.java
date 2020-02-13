package com.neusoftmedical.neumiva.dicompro.listener;

import com.neusoftmedical.neumiva.dicompro.ui.FileProcessForm;
import com.neusoftmedical.neumiva.dicompro.utils.CustomFileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffer on 2020/2/7.
 */
public class TargetFileListener implements ActionListener {

    private JFileChooser fileChooser = new JFileChooser();

    private FileProcessForm fileProcessForm;

    public TargetFileListener(FileProcessForm fileProcessForm) {
        this.fileProcessForm = fileProcessForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = "";
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        fileChooser.showDialog(new JLabel(), "选择");
        File file = fileChooser.getCurrentDirectory();
        fileProcessForm.getTargetPath().setText(file.getAbsolutePath());
    }
}
