package com.sxj.carloan.service;

import java.util.ArrayList;

/**
 * Created by admin on 2017/8/18.
 */

public class FileData {
    public ArrayList<FileInfo> fileInfos;
    public ArrayList<Integer> selectedId;
    public String path;
    public boolean searchingTag = false;

    public FileData(ArrayList<FileInfo> fileInfos,
                    ArrayList<Integer> selectedId, String path) {
        if (fileInfos == null)
            this.fileInfos = new ArrayList<FileInfo>();
        else
            this.fileInfos = fileInfos;
        if (selectedId == null)
            this.selectedId = new ArrayList<Integer>();
        else
            this.selectedId = selectedId;
        if (path == null)
            this.path = "/sdcard";
        else
            this.path = path;
    }
}
