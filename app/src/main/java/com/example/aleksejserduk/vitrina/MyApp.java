package com.example.aleksejserduk.vitrina;

import android.app.Application;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MyApp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder("2e0da377-2db7-4cf8-8796-b0cbf734715a");
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());

        YandexMetrica.enableActivityAutoTracking(this);

    }
}
