package pers.hexuren.cs;

import android.app.Application;
import android.content.Intent;

import pers.hexuren.cs.server.service.HttpService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, HttpService.class));
    }
}
