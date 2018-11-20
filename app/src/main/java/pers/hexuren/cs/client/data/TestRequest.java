package pers.hexuren.cs.client.data;

import java.util.HashMap;
import java.util.Map;

import pers.hexuren.cs.client.http.HttpRequest;
import pers.hexuren.cs.client.setting.ClientSetting;

public class TestRequest extends HttpRequest<TestResponse> {

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("test", "hello");
        return super.getParams();
    }

    @Override
    protected String getUrl() {
        return ClientSetting.getHttpServerIp() + "/test";
    }
}
