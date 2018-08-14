package com.example.aleksejserduk.vitrina;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.insurance.apps.R;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity{

    Context context = this;

    String url_land;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        TextView label = findViewById(R.id.label);
        label.setText(getIntent().getExtras().getString("name"));

        String url_logo = getIntent().getExtras().getString("logo");
        ImageView imageView = (ImageView) findViewById(R.id.logo);
        Picasso.with(context).load(url_logo).into(imageView);

        TextView longdescriptions = findViewById(R.id.textViewLong);
        longdescriptions.setText(getIntent().getExtras().getString("l_info"));

        TextView shortdescriptions = findViewById(R.id.textViewShort);
        shortdescriptions.setText(getIntent().getExtras().getString("s_info"));


        url_land = getIntent().getExtras().getString("link");

    }

    public void onClick(View v){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.startAnimation(animAlpha);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_land));
        startActivity(browserIntent);
    }

    public void onBack(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        view.startAnimation(animAlpha);
        super.onBackPressed();
        finish();
    }
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}
