package com.android.sgdy.coreutil;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 上官丹意 on 2016/06/30 07:22.
 */
public class ToastUtil {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
