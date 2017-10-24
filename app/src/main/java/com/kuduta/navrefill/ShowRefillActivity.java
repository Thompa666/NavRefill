package com.kuduta.navrefill;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowRefillActivity extends AppCompatActivity {

    ArrayList<String> numPhone = new ArrayList<>();
    String strMoney ,strPwd;
    private DatabaseHandler myDB;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_refill);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final String strDate = format.format(date);

        numPhone = getIntent().getStringArrayListExtra("numPhArrayList");



        ///Display Data for refill

        ListView listView = (ListView) findViewById(R.id.lvShowReff);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_expandable_list_item_1, numPhone);

        listView.setAdapter(adapter);




        Button bthome = (Button)findViewById(R.id.btHomeshrf);
        bthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowRefillActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button btpay = (Button)findViewById(R.id.btpayshrf);
        btpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ShowRefillActivity.this);

                builder.setTitle(R.string.app_refill);

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_pay,null);
                builder.setView(view);

                final  EditText mnyEditText = (EditText)view.findViewById(R.id.txtpaymoney);
                final EditText pwdEditText = (EditText)view.findViewById(R.id.txtpaypass);


                builder.setPositiveButton(R.string.app_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        strMoney = mnyEditText.getText().toString().trim();
                        strPwd = pwdEditText.getText().toString().trim();

                        TelephonyManager telecomManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                        for(int i = 0 ; i < numPhone.size();i++){
                            while (telecomManager.CALL_STATE_IDLE == 1){}
                            refillSUUD(strDate, numPhone.get(i),strPwd,strMoney);
                            Toast.makeText(getBaseContext(), "ได้เติมเงินหมายเลข :  "+ numPhone.get(i)  +" แล้ว", Toast.LENGTH_SHORT).show();
                        }//end for loop



                        Intent intent = new Intent(ShowRefillActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton(R.string.app_cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                builder.show();

                //Back to home
//                Intent intent = new Intent(ShowRefillActivity.this,MainActivity.class);
//                startActivity(intent);

            }
        });

    }
    public void refillSUUD(String daterefill, String num, String pwd ,String money){

        myDB = new DatabaseHandler(this);

//        String ussdCode = "*140*1*"+num+"*"+money+ Uri.encode("#");

//        String ussdCode = "*211*1*"+num+"*"+pwd +"*"+ money+"*1"+ Uri.encode("#");
//        startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:"+ussdCode)));

        if(myDB.addRecord(daterefill, num, Integer.parseInt(money), "Refill")== 1){

            Toast.makeText(getBaseContext(),"Return false ",Toast.LENGTH_SHORT).show();

        }else {

//                    insertData(String dataTimeCall, String numberPhone, String moneyRefill, String status)
            Toast.makeText(getBaseContext(), num  +" is susses.", Toast.LENGTH_SHORT).show();
        }
    }
}
