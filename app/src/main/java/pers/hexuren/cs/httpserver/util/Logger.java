package pers.hexuren.cs.httpserver.util;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import pers.hexuren.cs.BuildConfig;


public class Logger {

    private static boolean DEBUG = BuildConfig.DEBUG;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void writeToFile(String msg, File file, boolean append) {
        try {
            if(!file.exists()) file.createNewFile();
            FileWriter fw = new FileWriter(file, append);
            fw.write(sdf.format(new Date()) + ": " + msg + "\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
