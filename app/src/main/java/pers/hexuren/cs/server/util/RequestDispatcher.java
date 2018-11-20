package pers.hexuren.cs.server.util;


import pers.hexuren.cs.server.contorller.Controller;
import pers.hexuren.cs.server.contorller.impl.TestController;

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
