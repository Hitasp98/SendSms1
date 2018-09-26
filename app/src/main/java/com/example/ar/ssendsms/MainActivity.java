package com.example.ar.ssendsms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permissions;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS=0;
    Button sndbtn;
    EditText txtphoneNo, txtMessage;
    String phoneNo, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sndbtn = (Button) findViewById(R.id.btnSendSms);
        txtphoneNo = (EditText) findViewById(R.id.ed1);
        txtMessage = (EditText) findViewById(R.id.ed2);
        sndbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }

    protected void sendSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS);
            }
        }
    }
@Override
        public  void onRequestPermissionsResult(int requestCodem,String permissions[],int[] grantResults)
        {
            switch (requestCodem)
            {
                case PERMISSIONS:
                    if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {
                        SmsManager smsManger=SmsManager.getDefault();
                        smsManger.sendTextMessage(phoneNo,null,message,null,null);
                        Toast.makeText(getApplicationContext(),"Sms sent",Toast.LENGTH_LONG).show();
                    }else {Toast.makeText(getApplicationContext(),"Sms faild,Plese try agin",Toast.LENGTH_LONG).show();return;}
            }
        }

    }

