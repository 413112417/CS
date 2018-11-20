package pers.hexuren.cs.httpserver.contorller;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.response.Response;

/**
 * Created by hexuren on 18-5-14.
 */

public interface Controller {

    Response handle(IHTTPSession session) throws Exception;
}
