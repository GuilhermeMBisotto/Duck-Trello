package com.guilhermemorescobisotto.ducktrello.Helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.guilhermemorescobisotto.ducktrello.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public class Essential {

    public static void displayMessage(Context ctt, String title, String msg) {
        new AlertDialog.Builder(ctt)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(ctt.getString(R.string.gblOk), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void displayToast(Context ctt, String message) {
        Toast.makeText(ctt, message, Toast.LENGTH_SHORT).show();
    }

    public static void log(String msg) {
        Log.d("====================", "====================");
        Log.d("====================", msg);
        Log.d("====================", "====================");
    }

    public static String getDateWithFormat(Date date, String format, TimeZone tZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(tZone);
        return sdf.format(date);
    }

    public static String getDateWithFormat(Date date, String format) {
        return getDateWithFormat(date, format, TimeZone.getDefault());
    }

    public static String removeLastChar(String text) {
        return removeCharFromEnd(text, 1);
    }

    public static String removeCharFromEnd(String text, int quantity) {
        return text.substring(0, text.length() - quantity);
    }

    public static String getAppVersionName(Context ctx) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "0.0";
        }
    }
}
