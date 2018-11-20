package pers.hexuren.cs.server;

import android.content.Context;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.request.Method;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import pers.hexuren.cs.server.contorller.Controller;
import pers.hexuren.cs.server.util.FileUtil;
import pers.hexuren.cs.server.util.Logger;
import pers.hexuren.cs.server.util.RequestDispatcher;
import pers.hexuren.cs.server.util.ResourceReader;


/**
 * Created by hexuren on 18-5-14.
 */

public class HttpServer extends NanoHTTPD {

    private static final String TAG = "HttpServer";

    private static HttpServer httpServer;
    private static Context context;
    private ByteArrayOutputStream exceptionBos = new ByteArrayOutputStream();
    private PrintWriter exceptionPw = new PrintWriter(exceptionBos);

    private HttpServer(int port) {
        super(port);
    }

    public static synchronized void init(Context context, int port) {
        HttpServer.context = context;
        httpServer = new HttpServer(port);
        FileUtil.init(context);
        try {
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.d(TAG, "init success");
    }

    public static synchronized void destroy() {
        httpServer = null;
        Logger.d(TAG, "destroy success");
    }

    public static HttpServer getInstance() {
        if (httpServer == null) {
            throw new IllegalAccessError("Please initiate instance before using");
        }
        return httpServer;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public Response handle(IHTTPSession session) {
        try {
            if (Method.POST.equals(session.getMethod())) {
                //根据uri生成处理器
                Controller controller = RequestDispatcher.dispatch(session.getUri());

                if (controller == null) {
                    //如果找不到对应的处理器,尝试去寻找资源
                    return Response.newFixedLengthResponse(Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "URI NOT FOUND");
                }
                //处理请求,返回结果
                Response response = controller.handle(session);

                return response;
            } else if(Method.GET.equals(session.getMethod())) {
                return ResourceReader.readResource(session);
            }
            return null;
        } catch (Exception e) {
            exceptionBos.reset();
            e.printStackTrace(exceptionPw);
            exceptionPw.flush();
            return Response.newFixedLengthResponse(Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, "SERVER ERROR:\n" + exceptionBos.toString());
        }
    }
}
