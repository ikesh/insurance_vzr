package com.example.aleksejserduk.vitrina;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.insurance.apps.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    String[][] array = new String[6][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        String answer = getIntent().getExtras().getString("answer");
        try {
            JSONObject obj = new JSONObject(answer);
            String offers = obj.getString("offers");
            Log.e("offers", offers);
            for (int x = 1; x < 5; x++) {
                String ID = "id" + x;
                JSONObject offersObj = new JSONObject(offers);
                String id = offersObj.getString(ID);
                JSONObject objID = new JSONObject(id);
                array[x][0] = objID.getString("l_info");
                array[x][1] = objID.getString("link");
                array[x][2] = objID.getString("logo");
                array[x][3] = objID.getString("name");
                array[x][4] = objID.getString("s_info");

            }
            TextView offer1_textInfo1 = findViewById(R.id.offer1_textInfo2);
            offer1_textInfo1.setText(array[1][4]);
            ImageView imageView = (ImageView) findViewById(R.id.offer1_logo);
            Picasso.with(context).load(array[1][2]).into(imageView);
            Button offer1_action = (Button) findViewById(R.id.offer1_action);
            offer1_action.setContentDescription(array[1][1]);


            // -------------------------------------------------------------------offer2
            TextView offer2_textInfo1 = findViewById(R.id.offer2_textInfo2);
            offer2_textInfo1.setText(array[2][4]);
            ImageView imageView2 = (ImageView) findViewById(R.id.offer2_logo);
            Picasso.with(context).load(array[2][2]).into(imageView2);
            Button offer2_action = (Button) findViewById(R.id.offer2_action);
            offer2_action.setContentDescription(array[2][1]);

            // -------------------------------------------------------------------offer3
            TextView offer3_textInfo1 = findViewById(R.id.offer3_textInfo2);
            offer3_textInfo1.setText(array[3][4]);
            Log.e("idddddddddd", array[3][4]);
            ImageView imageView3 = (ImageView) findViewById(R.id.offer3_logo);
            Picasso.with(context).load(array[3][2]).into(imageView3);
            Button offer3_action = (Button) findViewById(R.id.offer3_action);
            offer3_action.setContentDescription(array[3][1]);

            // -------------------------------------------------------------------offer4
            TextView offer4_textInfo1 = findViewById(R.id.offer4_textInfo2);
            offer4_textInfo1.setText(array[4][4]);
            ImageView imageView4 = (ImageView) findViewById(R.id.offer4_logo);
            Picasso.with(context).load(array[4][2]).into(imageView4);
            Button offer4_action = (Button) findViewById(R.id.offer4_action);
            offer4_action.setContentDescription(array[4][1]);
            // -------------------------------------------------------------------offer5

            // -------------------------------------------------------------------offer6


        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + answer + "\"");
        }
    }
    public void onClick(View v){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.startAnimation(animAlpha);
        String url = v.getContentDescription().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
    public void nextPage(View v){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.startAnimation(animAlpha);
        String ID = v.getContentDescription().toString();
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(MainActivity.this, InfoActivity.class);
        infoIntent.putExtra("l_info", array[i][0]);
        infoIntent.putExtra("link", array[i][1]);
        infoIntent.putExtra("logo", array[i][2]);
        infoIntent.putExtra("name", array[i][3]);
        infoIntent.putExtra("s_info", array[i][4]);
        startActivity(infoIntent);
    }
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}
