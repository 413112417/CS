package pers.hexuren.cs.client.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import pers.hexuren.cs.R;

public class ClientSetting {

    private static ClientSetting clientSetting = new ClientSetting();
    private static SharedPreferences sp;
    private static Resources rs;

    private String httpServerIp;

    public static void load(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        rs = context.getResources();

        clientSetting.httpServerIp = sp.getString(rs.getString(R.string.pref_key_http_server_ip), rs.getString(R.string.pref_default_http_server_ip));
    }

    public static String getHttpServerIp() {
        return clientSetting.httpServerIp;
    }
}
