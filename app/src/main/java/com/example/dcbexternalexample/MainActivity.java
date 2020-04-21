package com.example.dcbexternalexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.buongiorno.newton.NewtonError;
import com.docomodigital.sdk.DcbExternal;
import com.docomodigital.sdk.dcb.interfaces.DcbCallback;

import com.docomodigital.sdk.dcb.model.DocomoUser;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DcbExternal.dcbRecognise(new DcbCallback() {
            @Override
            public void onSuccess(DocomoUser dcbUser) {
                runOnUiThread(() -> {
                    //User is recognised as Docomo Acquisition​
                    ((TextView) findViewById(R.id.text)).setText("subscribed: " + dcbUser.isSubscribed);

                    Toast.makeText(MainActivity.this.getApplicationContext(), "msisdn: "+ dcbUser.getId(MainActivity.this.getApplicationContext()), Toast.LENGTH_LONG).show();


                    //SAVE dcbUser.utcExpirationUnixTime IN THE PREFERENCE TO CHECK THE SUBSCRIPTION IN THE APP
                    if (dcbUser.utcExpirationUnixTime > System.currentTimeMillis() / 1000){
                        //user is premium
                        ((TextView) findViewById(R.id.text)).setText("subscribed: " + dcbUser.isSubscribed +
                                "; expireday unix: " + dcbUser.utcExpirationUnixTime +
                                "; expire date: " + new Date(dcbUser.utcExpirationUnixTime * 1000).toString());
                    }

                    else{
                        //user is expired
                        ((TextView) findViewById(R.id.text)).setText("subscribed: " + dcbUser.isSubscribed +
                                "; expireday unix: " + dcbUser.utcExpirationUnixTime +
                                "; expire date: " + new Date(dcbUser.utcExpirationUnixTime * 1000).toString());
                    }



                    if (dcbUser.isSubscribed) {
                        //User is subscribed, goto ahead with product​

                        ((TextView) findViewById(R.id.text)).setText("subscribed: " + dcbUser.isSubscribed +
                                "; expireday unix: " + dcbUser.utcExpirationUnixTime +
                                "; expire date: " + new Date(dcbUser.utcExpirationUnixTime * 1000).toString());
                    } else {
                        //User expired, not subscribed​
                        //user must pay again to access the product​
                        dcbUser.openAcquisitionPage(MainActivity.this);
                        finish();


                    }
                });

            }

            @Override
            public void onFailure(NewtonError docomoError) {
                runOnUiThread(() -> {

                    //Error informations are stored in newtonError Object.​
                    Log.d("", "failed recognise" + docomoError.getInfo());
                    ((TextView) findViewById(R.id.text)).setText("subscription failed");

                    //you're here because the user discovered the app on Google Play​
                    //go on with your code​

                });
            }
        });


    }
}
