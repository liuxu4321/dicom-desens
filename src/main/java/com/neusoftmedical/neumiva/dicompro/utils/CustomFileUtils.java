package com.neusoftmedical.neumiva.dicompro.utils;

import org.apache.commons.io.FileUtils;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffer on 2020/2/7.
 */
public class CustomFileUtils {



    public static void traverseFolder2(String path, List<File> fileList) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                //System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath(), fileList);
                    } else {
                        fileList.add(file2);
                        //System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }



    public static void main(String[] args){

    }
}

