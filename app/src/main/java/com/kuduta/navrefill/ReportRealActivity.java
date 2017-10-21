package com.kuduta.navrefill;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportRealActivity extends AppCompatActivity {

    private DatabaseHandler myDB;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_real);


        myDB = new DatabaseHandler(this);

        textView = (TextView)findViewById(R.id.tvRepText);
        Button cancelbtn = (Button)findViewById(R.id.btnRepExit);
        Button reloadbtn = (Button)findViewById(R.id.btnRepReload);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReportRealActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        reloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ShowReport();

            }
        });



    }

    private void ShowReport(){

        /*
                for  insert data test log
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = format.format(date);

                if(myDB.addRecord(strDate,"0632615536" , 20, "Refill")== 1){

                    Toast.makeText(getBaseContext(),"Return false ",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(getBaseContext(), "Insert OK", Toast.LENGTH_SHORT).show();
                }

*/

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
    }//End Showreport
}
