package com.android.sgdy.coreutil;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 上官丹意 on 2016/05/30 08:46.
 */
public class CommonUtil {
    public static String getBase64String(File file) {
        byte[] buffer = new byte[0];
        try {
            FileInputStream inputFile = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }
}
