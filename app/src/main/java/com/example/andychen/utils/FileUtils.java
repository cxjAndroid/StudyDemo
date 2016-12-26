package com.example.andychen.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by chenxujun on 16-10-26.
 */

public class FileUtils {
    /**
     * 将文件转成base64 字符串
     *
     * @param path 文件路径
     *
     * @return 失败则返回""
     */
    public static String encodeBase64File(String path){
        try {
            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        }catch (Exception e){
            LogUtils.e(e);
        }
        return "";
    }
}
