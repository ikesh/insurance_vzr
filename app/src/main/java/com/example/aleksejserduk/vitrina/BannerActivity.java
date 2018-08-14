package com.example.aleksejserduk.vitrina;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.insurance.apps.R;

public class BannerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);


        TextView tvTermsOfUse = (TextView) findViewById(R.id.bannertext);
        tvTermsOfUse.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void onBack(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        view.startAnimation(animAlpha);
        super.onBackPressed();
        finish();
    }
}
