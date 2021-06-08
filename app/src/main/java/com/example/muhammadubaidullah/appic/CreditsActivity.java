package com.example.muhammadubaidullah.appic;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        TextView logo= (TextView)findViewById(R.id.logo);
        Typeface logotypeface= Typeface.createFromAsset(getAssets(),"fonts/flaming.otf");
        logo.setTypeface(logotypeface);

        TextView creditstext=(TextView)findViewById(R.id.creditsinside);
        Typeface creditstexttypeface= Typeface.createFromAsset(getAssets(),"fonts/lato.ttf");
        creditstext.setTypeface(creditstexttypeface);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ImageView linkedIn = (ImageView)findViewById(R.id.linkedin);
        ImageView github=(ImageView)findViewById(R.id.github);
        ImageView gmail= (ImageView)findViewById(R.id.gmail);

        final String LINKED_IN="https://www.linkedin.com/in/muhammad-ubaid-ullah-2a0976153/";
        final String GITHUB="https://github.com/UbaidAsim";
        final String GMAIL="mullah.bese17seecs@seecs.edu.pk";

        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(LINKED_IN));
                startActivity(i);
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(GITHUB));
                startActivity(i);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"+GMAIL));
                i.putExtra(Intent.EXTRA_EMAIL,GMAIL);
                i.putExtra(Intent.EXTRA_SUBJECT,"Appic App Feedback");
                i.putExtra(Intent.EXTRA_TEXT,"Compose your email here.");
                if(i.resolveActivity(getPackageManager())!= null){
                    startActivity(i);
                }


            }
        });
    }

}
