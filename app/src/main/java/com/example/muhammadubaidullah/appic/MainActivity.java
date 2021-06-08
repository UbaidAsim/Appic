package com.example.muhammadubaidullah.appic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//Almost Final
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        TextView LetsStart = (TextView) findViewById(R.id.start);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/bianka.otf");
        LetsStart.setTypeface(typeface);



        TextView Credits=(TextView)findViewById(R.id.Credits);
        Typeface creditstypeface= Typeface.createFromAsset(getAssets(),"fonts/bebas.ttf");
        Credits.setTypeface(creditstypeface);


        LetsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,ContainerActivityTwo.class);
                startActivity(i);
            }});

        Credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,CreditsActivity.class);
                startActivity(i);
            }});







    }



}

