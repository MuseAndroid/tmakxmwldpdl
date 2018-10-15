/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.finance.smartga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

public class MainActivity extends CordovaActivity {

    protected static final String TAG = "smartGA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml

        loadUrl(launchUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new getMarketVersion().execute();
    }

    private void clearApplicationCache(java.io.File dir) {
        if (dir == null) {
            dir = getCacheDir();
        } else { }
        if (dir == null) {
            return;
        } else { }

        java.io.File[] children = dir.listFiles();

        try {
            for(int i = 0; i < children.length; i++) {
                if (children[i].isDirectory()) {
                    clearApplicationCache(children[i]);
                } else {
                    children[i].delete();
                }
            }
        }  catch (Exception e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearApplicationCache(null);
    }

    private class getMarketVersion extends AsyncTask<Void, Void, String> {

        String verSion;
        String marketVersion;
        AlertDialog.Builder mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                Document doc = Jsoup
                        .connect(
                                "https://play.google.com/store/apps/details?id=" + getPackageName() )
                        .get();
                Elements Version = doc.select(".htlgb ");

                for (int i = 0; i < 15 ; i++) {
                    marketVersion = Version.get(i).text();
                    if (Pattern.matches("^[0-9]{1}.[0-9]{1}.[0-9]{1}$", marketVersion)) {
                        break;

                    }
                }
                return marketVersion;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            PackageInfo pi = null;
            try {
                pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            try {
                verSion = pi.versionName;
                marketVersion = result;
                Log.d(TAG, "기기 버전 = " + verSion);
                Log.d(TAG, "마켓 버전 = " + marketVersion);

                String versionData[] = verSion.split("[.]");
                String mVersionData[] = marketVersion.split("[.]");
                String strDVersion = "";
                String strMVersion = "";
                int dVersion;
                int mVersion;

                if (!"null".equals(marketVersion)) {
                    for (int i = 0; i < versionData.length; i++) {
                        strDVersion += versionData[i];
                        strMVersion += mVersionData[i];
                    }
                    dVersion = Integer.parseInt(strDVersion);
                    mVersion = Integer.parseInt(strMVersion);
                    Log.d(TAG, "앱 버전 값 = " + dVersion);
                    Log.d(TAG, "마켓 버전 값 = " + mVersion);
                    if (dVersion >= mVersion) {
                        Log.d(TAG, "최신버전입니다.");
//                            Toast.makeText(MainActivity.this, "현재 최신버전입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("업데이트")
                                .setMessage("마켓에 새로운 버전이 있습니다. \n 보다 나은 사용을 위해 업데이트 해 주세요")
                                .setPositiveButton("업데이트 하기", new AlertDialog.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                                        startActivity(mIntent);
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                    }
                } else {
                    Log.d(TAG, "마켓 서버 에러...");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
}
