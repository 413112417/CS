package pers.hexuren.cs.client.data;

import java.util.HashMap;
import java.util.Map;

import pers.hexuren.cs.client.http.HttpRequest;

public class TestRequest extends HttpRequest<TestResponse> {

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("test", "hello");
        return super.getParams();
    }

    @Override
    protected String getUrl() {
        return "http://127.0.0.1:8000/test";
    }
}
