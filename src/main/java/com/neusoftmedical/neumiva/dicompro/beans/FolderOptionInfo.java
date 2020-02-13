package com.neusoftmedical.neumiva.dicompro.beans;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class FolderOptionInfo {

    private String sourceFolder;

    private String targetFolder;

    private int coverFlag = 0; //0：不覆盖；1：原路径覆盖

    public FolderOptionInfo() {
    }

    public FolderOptionInfo(String sourceFolder, String targetFolder, int coverFlag) {
        this.sourceFolder = sourceFolder;
        this.targetFolder = targetFolder;
        this.coverFlag = coverFlag;
    }

    public String getSourceFolder() {
        return sourceFolder;
    }

    public String getTargetFolder() {
        return targetFolder;
    }

    public int getCoverFlag() {
        return coverFlag;
    }

    public void setSourceFolder(String sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    public void setTargetFolder(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public void setCoverFlag(int coverFlag) {
        this.coverFlag = coverFlag;
    }
}
