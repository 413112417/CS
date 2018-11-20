package pers.hexuren.cs.httpserver.contorller.impl;

import com.alibaba.fastjson.JSON;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;

import pers.hexuren.cs.httpserver.contorller.Controller;
import pers.hexuren.cs.httpserver.data.bean.TestResponse;

public class TestController implements Controller {

    @Override
    public Response handle(IHTTPSession session) throws Exception {
        TestResponse testResponse = new TestResponse();
        testResponse.setMsg("this is from http server");
        return Response.newFixedLengthResponse(Status.OK, NanoHTTPD.MIME_PLAINTEXT,
                JSON.toJSONString(testResponse));
    }
}
