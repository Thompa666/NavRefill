package com.kuduta.navrefill;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DisplayActivity extends AppCompatActivity {

    ArrayList<String> numPhone = new ArrayList<>();
    String strMoney ,strPwd;
    private DatabaseHandler myDB;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        final TelephonyManager telecomManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final String strDate = format.format(date);

//        numPhone = getIntent().getExtras().getString("numPhArrayList");

        numPhone = getIntent().getStringArrayListExtra("numPhArrayList");

        final ListView listView = (ListView) findViewById(R.id.listViewdisplay);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_expandable_list_item_1, numPhone);
//                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        Button bthome = (Button) findViewById(R.id.btHome);
        bthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button payButton = (Button) findViewById(R.id.btpay);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder  = new AlertDialog.Builder(DisplayActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_pay, null);

                builder.setView(view);
                EditText mnyEditText = (EditText) findViewById(R.id.paymoney);
                EditText pwdEditText = (EditText) findViewById(R.id.paypass);


                strMoney = mnyEditText.getText().toString().trim();
                strPwd = pwdEditText.getText().toString().trim();

                Toast.makeText(getApplicationContext(), "Money is : "+ strMoney  +"; Password is : "+ strPwd, Toast.LENGTH_SHORT).show();

            }
        });
       payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                EditText mnyEditText = (EditText) findViewById(R.id.edtmny);
//                EditText pwdEditText = (EditText) findViewById(R.id.edtpwd);

               //New edit 19.10.2017
                AlertDialog.Builder builder  = new AlertDialog.Builder(DisplayActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_pay, null);

                builder.setView(view);
                EditText mnyEditText = (EditText) findViewById(R.id.paymoney);
                EditText pwdEditText = (EditText) findViewById(R.id.paypass);


                strMoney = mnyEditText.getText().toString().trim();
                strPwd = pwdEditText.getText().toString().trim();

                Toast.makeText(getApplicationContext(), "Money is : "+ strMoney  +"; Password is : "+ strPwd, Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Money is : "+ strMoney  +"; Password is : "+ strPwd, Toast.LENGTH_SHORT).show();

                progressDialog = new ProgressDialog(DisplayActivity.this);
                progressDialog.setMessage("Processing..");
                progressDialog.show();

                for(int i =0; i< numPhone.size(); i++){

//                    Toast.makeText(getApplicationContext(),numPhone.get(i),Toast.LENGTH_SHORT).show();

                    while (telecomManager.CALL_STATE_IDLE == 1){}

                    refillSUUD(numPhone.get(i),strPwd,strMoney);

                    Toast.makeText(getApplicationContext(),numPhone.get(i),Toast.LENGTH_SHORT).show();



                    //Log status in database
                    myDB.addRecord(strDate, numPhone.get(i),Integer.parseInt(strMoney),"Refill");

                }

                progressDialog.hide();

                Intent hintent = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(hintent);
            }
        });

    }
    public void refillSUUD(String num, String pwd ,String money){

//        String ussdCode = "*140*1*"+num+"*"+money+ Uri.encode("#");
//        new ussd
        String ussdCode = "*211*1*"+num+"*"+pwd +"*"+ money+"*1"+Uri.encode("#");
        startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:"+ussdCode)));

    }



}
