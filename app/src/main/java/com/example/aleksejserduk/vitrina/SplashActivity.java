package com.example.aleksejserduk.vitrina;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.insurance.apps.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    Timer timer = new Timer();
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        cd = new ConnectionDetector(this);
        if (cd.isConnected()) {
            new GetTask().execute();
        }
        else {
            Toast.makeText(SplashActivity.this, R.string.neednetwork, Toast.LENGTH_SHORT).show();
            timer.schedule(new TimerTask() {
                public void run() {
                    SplashActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            finish();
                        }
                    });
                }
            }, 5000);
        }
        SwipeRefreshLayout back = (SwipeRefreshLayout) findViewById(R.id.swipe);
        back.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
    private class GetTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getAnswer();
            return null;
        }
    }
    public void getAnswer() {
        try {
            String urljson = getString(R.string.json);
            URL url = new URL(urljson);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            final int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));
                String answer = "";
                String line = null;
                while ((line = reader.readLine()) != null) {
                    answer += line;
                }
                reader.close();
                final String finalAnswer = answer;
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("answer", finalAnswer);
                startActivity(intent);
                finish();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}

