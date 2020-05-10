package com.example.pa2576;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeProfile extends AppCompatActivity {
    private Button change;
    private EditText firstName, lastName, email, username, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        TextEditHandler();


    }

    public void TextEditHandler(){
         firstName = (EditText) findViewById(R.id.FirstNameET);
         lastName = (EditText) findViewById(R.id.LastNameET);
         email = (EditText) findViewById(R.id.EmailET);
         username = (EditText) findViewById(R.id.usernameET);
         password = (EditText) findViewById(R.id.PasswordET);
         password2 = (EditText) findViewById(R.id.PasswordET2);

        change = (Button) findViewById(R.id.ChangeBtn);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTextDatabase(firstName.toString(),lastName.toString(),email.toString(),username.toString(),password.toString(),password2.toString());
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
            }
        });

    }
    public void UpdateTextDatabase(String first, String last, String email, String username, String password, String password2) {
        if (first.equals(null) && last.equals(null) && email.equals(null) && username.equals(null)) {
            if (checkPassword(password) == true) {
                if (password.equals(password2)) {
//            updateDatabase;

                }

            }
        } else if (first.equals(null) && last.equals(null) && email.equals(null)) {
            if (checkPassword(password) == true) {
                if (password.equals(password2)) {
//            updateDatabase;

                }

            }

        } else if (first.equals(null) && last.equals(null) && email.equals(null) && password.equals(null)){
            //            updateDatabase;
        }
        else {
            //            updateDatabase;
        }
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
