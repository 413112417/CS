package pers.hexuren.cs.client.http;

/**
 * Created by hexuren on 18-5-9.
 */

public interface HttpCallback<T> {
    /**
     * 成功的回调
     */
    void onResponse(T httpResponse);

    /**
     * 失败的回调
     */
    void onError(Throwable t);
}
