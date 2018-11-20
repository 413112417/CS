package pers.hexuren.cs;

import android.app.Application;
import android.content.Intent;

import pers.hexuren.cs.client.setting.ClientSetting;
import pers.hexuren.cs.server.service.HttpService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //加载客户端配置
        ClientSetting.load(this);

        //开启http服务器
        startService(new Intent(this, HttpService.class));
    }
}
