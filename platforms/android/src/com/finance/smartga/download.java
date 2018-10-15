/*package com.finance.smartga;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.util.List;

*//**
 * Created by junho on . 11. 7.2016.
 *//*
public class download {
    private Context mContext;
    private DownloadManager mDownloadManager;
    private int mDownloadQueueId;
    private String mFileName;
    *//**
    * @param url : 파일을 다운로드 할 url.
    *//*

    public void download(String url) {
        if (mDownloadManager == null) {
            mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        }

        Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("파일 다운로드 중...");
        request.setDescription("게시판 첨부파일 다운로드 중...");
        List<String> pathSegmentList = uri.getPathSegments();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/temp").mkdirs();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS + "/temp/", pathSegmentList.get(pathSegmentList.size()-1));
        mFileName = pathSegmentList.get(pathSegmentList.size()-1);

        mDownloadQueueId = mDownloadManager.enqueue(request);
    }
}*/
