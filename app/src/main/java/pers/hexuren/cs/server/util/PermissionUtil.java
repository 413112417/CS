package pers.hexuren.cs.server.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理工具
 * Created by XJH on 2017/4/11.
 */

public class PermissionUtil {

    /**
     * 检查权限
     * @return true 全部授权 false 未全部授权
     */
    public static boolean havePermission(Activity activity, String... permission) {
        if(filterPermission(activity, permission) == null) {
            return true;
        }
        return false;
    }

    /**
     * 如果没有对应权限，则请求该权限
     * @param permission
     */
    public static void requestPermission(Activity activity, String... permission) {
        if(!havePermission(activity, permission)) {
            String[] permissionNotGranted = filterPermission(activity, permission);
            ActivityCompat.requestPermissions(activity,
                    permissionNotGranted, 0);
        }
    }

    /**
     * 筛选权限，去除已有的
     * @return
     */
    private static String[] filterPermission(Activity activity, String... permission) {

        if(permission == null || permission.length == 0) {
            return null;
        }

        List<String> permissionNotGranted = new ArrayList<>();
        for(int i=0; i<permission.length; i++) {
            if(ContextCompat.checkSelfPermission(activity, permission[i])
                    != PackageManager.PERMISSION_GRANTED) {
                permissionNotGranted.add(permission[i]);
            }
        }

        if(permissionNotGranted.size() > 0) {
            return permissionNotGranted.toArray(new String[permissionNotGranted.size()]);
        } else {
            return null;
        }
    }

    /**
     * 改变设备权限
     * @param path
     */
    public static void changeDevicePermission(String path) {
        File device = new File(path);

        /* Check access permission */
        if (!device.canRead() || !device.canWrite()) {
            Process su = null;
            DataOutputStream os = null;
            try {
                su = Runtime.getRuntime().exec("/system/xbin/su");
                os = new DataOutputStream(su.getOutputStream());
                String cmd = "chmod 666 " + device.getAbsolutePath() + "\n"
                        + "exit\n";
                os.write(cmd.getBytes());
                os.flush();
                su.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                    su.destroy();
                } catch (Exception e) {
                }
            }
        }
    }
}