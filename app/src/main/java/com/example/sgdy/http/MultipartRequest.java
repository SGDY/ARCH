package com.example.sgdy.http;

import android.util.Base64;
import android.util.Log;

import com.android.sgdy.coreutil.CommonUtil;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by 上官丹意 on 2016/05/29 19:53.
 */
public class MultipartRequest extends Request<String> {

    private static final String TAG = "MultipartRequest";
    protected static final String PROTOCOL_CHARSET = "utf-8";
    // private MultipartEntity entity = new MultipartEntity();

    MultipartEntityBuilder mBuilder = MultipartEntityBuilder.create();
    HttpEntity mHttpEntity;
    private static final String FILE_PART_NAME = "memberFace";

    private final Response.Listener<String> mListener;
    private final File mFilePart;
    private final Map<String, String> mStringPart;

    public MultipartRequest(String url, Response.ErrorListener errorListener,
                            Response.Listener<String> listener, File file,
                            Map<String, String> mStringPart) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = file;
        if (mFilePart != null) {
            Log.d(TAG, "img-path>>" + file.getAbsolutePath());
            Log.d(TAG, "img-name>>" + file.getName());
        }
        this.mStringPart = mStringPart;
        mBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
        //NOTE: 1、添加图片
        if (mFilePart != null) {
//            mEntity.addPart(FILE_PART_NAME, new FileBody(mFilePart));
//            mBuilder.addBinaryBody(FILE_PART_NAME, mFilePart, ContentType.create("image/jpg,image/png"), mFilePart.getName());
            mBuilder.addBinaryBody(FILE_PART_NAME, Base64.encode(CommonUtil.getBase64String(mFilePart).getBytes(),Base64.DEFAULT));
//            mBuilder.addTextBody(FILE_PART_NAME, Base64.encodeToString(CommonUtil.getBase64String(mFilePart).getBytes(),Base64.DEFAULT));
        }
        //NOTE: 2、添加字符串部分--转化成utf-8编码
        if (mStringPart != null) {
            for (Map.Entry<String, String> entry : mStringPart.entrySet()) {
                try {
                    mBuilder.addBinaryBody(entry.getKey(), entry.getValue().getBytes(PROTOCOL_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBodyContentType() {
        return mHttpEntity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            mHttpEntity = mBuilder.build();
            mHttpEntity.writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.d(TAG, jsonString);
            return Response.success(jsonString, getCacheEntry());
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }
}
