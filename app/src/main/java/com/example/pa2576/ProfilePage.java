package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    Button changeProfile;
    Button changePic;
    Button rates;
    Button uploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        TextfieldHandler();

        changeProfile = findViewById(R.id.changeProfileBtn);
        changePic = findViewById(R.id.changePicBtn);
        rates = findViewById(R.id.rateBtn);
        uploads = findViewById(R.id.myUploadsBtn);

        changeProfile.setOnClickListener( this);
        changePic.setOnClickListener( this);
        rates.setOnClickListener( this);
        uploads.setOnClickListener( this);


    }

    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeProfileBtn:
                openChangeProfile();
                break;

            case R.id.changePicBtn:
                openChangePic();
                break;
            case R.id.rateBtn:
                openRates();
                break;
            case R.id.myUploadsBtn:
                openUploads();
                break;
        }

    }



    public void openChangeProfile(){
        Intent intent = new Intent(this, ChangeProfile.class);
        startActivity(intent);
    }

    public void openChangePic(){
        Intent intet = new Intent(this, ChangeProfilePic.class);
        startActivity(intet);
    }

    public void openRates(){
        Intent intent = new Intent(this, MyRates.class);
        startActivity(intent);
    }

    public void openUploads() {
        Intent intent = new Intent(this, MyUploads.class);
        startActivity(intent);
    }

    public void TextfieldHandler() {
        TextView rateAmount = (TextView) findViewById(R.id.AmountVoteTV);
        TextView myRates = (TextView) findViewById(R.id.RateingTV);

        myRates.setText("Your rates");
        rateAmount.setText("Here will be rates from database");
    }

}
