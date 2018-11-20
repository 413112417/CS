package pers.hexuren.cs.server.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import pers.hexuren.cs.server.HttpServer;

public class HttpService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HttpServer.init(this, 8000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpServer.destroy();
    }
}
