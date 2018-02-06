package com.iblogstreet.util;

import java.io.File;
import java.io.FilenameFilter;

public class FilterApk implements FilenameFilter {

    @Override
    public boolean accept(File file, String name) {
        return name.endsWith(".apk");
    }
}