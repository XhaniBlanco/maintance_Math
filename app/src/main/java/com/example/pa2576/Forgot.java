package com.example.pa2576;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        Button sendPassword = (Button) findViewById(R.id.ChangeBTN);
        final EditText email = (EditText) findViewById(R.id.EmailET);
        sendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmail(email.toString());
            }
        });


    }
    protected void sendEmail(String email){
        Log.i("Send email", "Your email has been sent");
        String TO[] = {email};
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        String newPassword = newPassword();

        sendEmail.setDataAndType(Uri.parse("mailto:"),"text");
        sendEmail.putExtra(Intent.EXTRA_EMAIL, TO);
        sendEmail.putExtra(Intent.EXTRA_SUBJECT,"New password for MathSolution");
        sendEmail.putExtra(Intent.EXTRA_TEXT,"Hello, " +
                "You entered that you forgot you username and/or password. Here comes a new password." +
                "username: " +
                "password: " + newPassword +
        "Best regards Math Solution");
        sendEmail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(Intent.createChooser(sendEmail, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Forgot.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    protected String newPassword(){
        final String characters  ="ABCDEFGHIJKLMNOPQRSTUVWabcdefghijklmnopqrstuv1234567890";
        StringBuilder password = new StringBuilder();
        int i = 8;
        boolean check = false;
        while(check!=true){
        while (i>0) {
            Random rnd = new Random();
            password.append(characters.charAt(rnd.nextInt(characters.length())));
            i--;
        }
        check=checkPassword(password.toString());
        }
        return password.toString();
    }
    public boolean checkPassword(String password) {
        boolean check = false;

        if(password.length()>=8){
            boolean checknumber = false;
            boolean checkCapLetter = false;
            for(int i=0; i<password.length();i++){

                if(Character.isDigit(password.charAt(i))){
                    checknumber = true;
                }
                if(Character.isUpperCase(password.charAt(i))){
                    checkCapLetter = true;
                }

            }
            if(checknumber && checkCapLetter){
                check = true;
            }
        }
        return check;
    }
}
