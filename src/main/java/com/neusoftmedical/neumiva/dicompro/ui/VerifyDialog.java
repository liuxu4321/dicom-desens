package com.neusoftmedical.neumiva.dicompro.ui;

import com.neusoftmedical.neumiva.dicompro.beans.DicomInfo;
import com.neusoftmedical.neumiva.dicompro.utils.DicomFileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;

public class VerifyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField filePath;
    private JButton doVerifyButton;
    private JList dicomTagList;
    private JButton selectFileButton;

    public VerifyDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        JFileChooser fileChooser = new JFileChooser();

        selectFileButton.addActionListener(e -> {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            fileChooser.showDialog(this, "选择");
            fileChooser.setSize(500,500);
            File file = fileChooser.getSelectedFile();
            filePath.setText(file.getPath());
        });


        doVerifyButton.addActionListener(e -> {
            String file = filePath.getText();
            if(StringUtils.isEmpty(file)){
                JOptionPane.showMessageDialog(this, "请选择文件", "消息", JOptionPane.DEFAULT_OPTION);
                return;
            }

            DicomInfo dicomInfo = DicomFileUtils.getDicomTagValue(file);
            if(dicomInfo == null) {
                JOptionPane.showMessageDialog(this, "非dicom或文件不存在", "消息", JOptionPane.DEFAULT_OPTION);
                return;
            }else{
                Vector vector = new Vector();

                vector.add("患者信息：");
                Field[] field = dicomInfo.getDicomPatientInfo().getClass().getDeclaredFields();
                for (int j = 0; j < field.length; j++) {
                    String listItem = "";
                    String name = field[j].getName();
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    try {
                        Method m = dicomInfo.getDicomPatientInfo().getClass().getMethod("get" + name);
                        String value = (String) m.invoke(dicomInfo.getDicomPatientInfo());
                        listItem = name + " : " + value;
                        vector.add(listItem);
                    } catch (NoSuchMethodException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }

                vector.add("医院信息：");
                field = dicomInfo.getDicomHospitalInfo().getClass().getDeclaredFields();
                for (int j = 0; j < field.length; j++) {
                    String listItem = "";
                    String name = field[j].getName();
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    try {
                        Method m = dicomInfo.getDicomHospitalInfo().getClass().getMethod("get" + name);
                        String value = (String) m.invoke(dicomInfo.getDicomHospitalInfo());
                        listItem = name + " : " + value;
                        vector.add(listItem);
                    } catch (NoSuchMethodException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }

                vector.add("检查信息：");
                field = dicomInfo.getDicomStudyInfo().getClass().getDeclaredFields();
                for (int j = 0; j < field.length; j++) {
                    String listItem = "";
                    String name = field[j].getName();
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    try {
                        Method m = dicomInfo.getDicomStudyInfo().getClass().getMethod("get" + name);
                        String value = (String) m.invoke(dicomInfo.getDicomStudyInfo());
                        listItem = name + " : " + value;
                        vector.add(listItem);
                    } catch (NoSuchMethodException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }
                dicomTagList.setListData(vector);
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VerifyDialog dialog = new VerifyDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
