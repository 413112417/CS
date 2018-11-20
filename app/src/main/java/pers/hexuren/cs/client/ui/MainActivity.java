package pers.hexuren.cs.client.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import pers.hexuren.cs.R;
import pers.hexuren.cs.client.data.TestRequest;
import pers.hexuren.cs.client.data.TestResponse;
import pers.hexuren.cs.client.http.HttpCallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TestRequest().postMap(new HttpCallback<TestResponse>() {
            @Override
            public void onResponse(TestResponse httpResponse) {
                Log.d("asd", "onResponse: " + httpResponse.getMsg());
            }

            @Override
            public void onError(Throwable t) {
                Log.d("asd", "onError: " + t.getMessage());
            }
        });
    }
}
