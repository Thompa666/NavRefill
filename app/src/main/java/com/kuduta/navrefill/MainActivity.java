package com.kuduta.navrefill;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private int mYear, mMonth, mDay;
    private DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button extButton = (Button) findViewById(R.id.btexit);
        extButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closeApp();
                    }
                }
        );

        Button btnReport = (Button) findViewById(R.id.btreport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reportIntent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(reportIntent);
            }
        });

        Button btnRefill = (Button) findViewById(R.id.btrefill);
        btnRefill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_custom, null);
                builder.setView(view);

                final EditText expEditText = (EditText) view.findViewById(R.id.expireDate);
                final EditText moneyEditText = (EditText) view.findViewById(R.id.moneyLife);

                final ImageButton imageButton = (ImageButton) view.findViewById(R.id.imbtCalen);
                imageButton.setOnClickListener(new View.OnClickListener() {
                                                   @RequiresApi(api = Build.VERSION_CODES.N)
                                                   @Override
                                                   public void onClick(View v) {

                                                       if (v == imageButton) {
                                                           final Calendar calendar = Calendar.getInstance();
                                                           mYear = calendar.get(Calendar.YEAR);
                                                           mMonth = calendar.get(Calendar.MONTH);
                                                           mDay = calendar.get(Calendar.DAY_OF_MONTH);

                                                           DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                                                                   new DatePickerDialog.OnDateSetListener() {
                                                                       @Override
                                                                       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                                                           expEditText.setText(year + "-" + (month + 1) + "-" + dayOfMonth);

                                                                       }
                                                                   }, mYear, mMonth, mDay);
                                                           datePickerDialog.show();
                                                       }
                                                   }
                                               }
                );


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        String strExpDate = CalDate(Integer.parseInt(expEditText.getText().toString()));
                        String strExpDate = expEditText.getText().toString().trim();
                        String strMonLife = moneyEditText.getText().toString().trim();

                        Toast.makeText(getApplicationContext(), "หมดอายุถึงวันที่ : " + strExpDate, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "จำนวนเงินที่เลือก : " + strMonLife, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, ReffActivity.class);
                        i.putExtra("expValue", strExpDate);
                        i.putExtra("monValue", strMonLife);
                        startActivity(i);
                        finish();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });
    }

    public void closeApp(){

        finish();
        moveTaskToBack(true);
        System.exit(0);


    }
}



