package pers.hexuren.cs.httpserver.util;


import pers.hexuren.cs.httpserver.contorller.Controller;
import pers.hexuren.cs.httpserver.contorller.impl.TestController;

/**
 * Created by hexuren on 18-5-14.
 */

public class RequestDispatcher {

    private static TestController testController = new TestController();

    public static Controller dispatch(String uri) {

        if ("/test".equals(uri)) {
            return testController;
        }

        return null;
    }
}
