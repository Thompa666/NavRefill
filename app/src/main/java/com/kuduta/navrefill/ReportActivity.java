package com.kuduta.navrefill;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity {

    private DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        TextView textView = (TextView) findViewById(R.id.tvreport);
        Cursor res = myDB.getAllData();
        StringBuffer stringBuffer = new StringBuffer();

        if(res !=null && res.getCount()>0){

            while (res.moveToNext()){

                stringBuffer.append(res.getString(0)+","+ res.getString(1)+","+res.getString(2)+","+res.getString(3)+","+res.getString(4)+"\n");
            }



            textView.setText(stringBuffer.toString());
            Toast.makeText(getApplicationContext(),"Data Retrieved Successfully",Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(getApplicationContext(),"No data retrieve",Toast.LENGTH_SHORT).show();

        }
        */

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Button btreload = (Button) findViewById(R.id.btreload);
        btreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        Button btTohome = (Button) findViewById(R.id.btrtohome);
        btTohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xrepIntent = new Intent(ReportActivity.this, MainActivity.class);
                startActivity(xrepIntent);
            }
        });

    }



}
