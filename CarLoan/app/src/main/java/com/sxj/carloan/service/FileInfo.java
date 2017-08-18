package com.sxj.carloan.service;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.util.Util;

import java.io.File;
import java.sql.Date;
import java.util.Comparator;

import static com.sxj.carloan.service.FileListAdapter.PHOTO;

/**
 * Created by admin on 2017/8/19.
 */

public class FileInfo implements Comparable<FileInfo>{
    int type;

    String name = null;
    String size = null;
    String desc;

    String path = null;
    String date = null;
    Long time ;
    boolean directory = false;
    boolean selected = false;
    Drawable dr = null;
    private View view = null;

    // String date,
    public FileInfo(String name, String path, int type, String size,
                    boolean directory, String desc,Long time) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.path = path;
        this.desc = desc;
        this.time = time;
        this.directory = directory;
    }

    public FileInfo(String path) {
        File file = new File(path);
        this.name = file.getName();
        this.desc = FileUtil.getDesc(file);
        this.size = String.valueOf(Util.fileSize(file.length()));

        this.type = FileUtil.switchIcon(file);
        this.path = path;
        // this.date = date;
        this.directory = file.isDirectory();
    }

    public final String name() {
        return this.name;
    }

    public final String path() {
        return this.path;
    }

    public final void invertSelected() {
        selected = !selected;
    }

    public final boolean selectted() {
        return selected;
    }

    public final void setSelected(boolean s) {
        selected = s;
    }

    public final String size() {
        if (size == null) {
            File file = new File(path);
            this.size = String.valueOf(Util.fileSize(file.length()));
            this.date = new Date(file.lastModified()).toLocaleString();
        }
        return this.size;
    }

    public final String date() {
        if (date == null) {
            File file = new File(path);
            this.size = String.valueOf(Util.fileSize(file.length()));
            this.date = new Date(file.lastModified()).toLocaleString();
        }
        return this.date;
    }

    public final void setView(View v) {
        view = v;
    }

    public final View getView() {
        return view;
    }

    public final boolean isPhoto() {
        return type == PHOTO;
    }

    public final synchronized void setDrawble(Drawable d) {
        dr = d;
    }

    public final synchronized Drawable getDrawable() {
        return dr;
    }

    public final boolean directory() {
        return this.directory;
    }

    public final int type() {
        return type;
    }

    public int compareTo(FileInfo another) {
        return another.time.compareTo(this.time);
    }
}
