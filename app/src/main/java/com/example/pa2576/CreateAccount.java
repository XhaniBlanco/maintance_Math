package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText eMail;
    EditText createUsername;
    EditText createPassword;
    EditText createPassword2;
    Button createAccount;
    CheckBox checkBoxGDPR;
    CheckBox checkBoxRules;
    TextView welcomeText;
    TextView notFilled;
    TextView passwordText;
    TextView passwordText2;
    TextView chooseEducation;
    CheckBox civAI;
    CheckBox civSak;
    CheckBox hogSak;
    CheckBox civIndek;
    CheckBox civMarin;
    CheckBox civMaskin;
    CheckBox hogMaskin;
    CheckBox civSpel;
    CheckBox hogSpel;
    CheckBox educationElse;

    ArrayList<CheckBox> educationArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        welcomeText = findViewById(R.id.welcome);
        checkBoxGDPR = findViewById(R.id.checkBoxGDPR);
        checkBoxRules = findViewById(R.id.checkBoxRules);
        firstName = findViewById(R.id.createFirstName);
        lastName = findViewById(R.id.createLastName);
        eMail = findViewById(R.id.createEmail);
        createUsername = findViewById(R.id.createUsername);
        createPassword = findViewById(R.id.createPassword);
        createPassword2 = findViewById(R.id.createPassword2);
        notFilled = findViewById(R.id.notFilled);
        passwordText = findViewById(R.id.passwordText);
        passwordText2 = findViewById(R.id.passwordText2);
        chooseEducation = findViewById(R.id.chooseEducation);


        //All buttons for educations
        civAI = findViewById(R.id.civAI);
        civSak = findViewById(R.id.civSak);
        hogSak = findViewById(R.id.hogSak);
        civIndek = findViewById(R.id.civIndek);
        civMarin = findViewById(R.id.civMarin);
        civMaskin = findViewById(R.id.civMaskin);
        hogMaskin = findViewById(R.id.hogMaskin);
        civSpel = findViewById(R.id.civSpel);
        hogSpel = findViewById(R.id.hogSpel);
        educationElse = findViewById(R.id.educationElse);

        //Array for the educations checkboxes


        educationArray.add(civAI);
        educationArray.add(civSak);
        educationArray.add(hogSak);
        educationArray.add(civIndek);
        educationArray.add(civMarin);
        educationArray.add(civMaskin);
        educationArray.add(hogMaskin);
        educationArray.add(civSpel);
        educationArray.add(hogSpel);
        educationArray.add(educationElse);

        createAccount = findViewById(R.id.createAccount);

        //when the create account button is pressed the system checks that all the requirements in checkIfFilled() is true meaning that every criteria for the account to be created is forfilled
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfFilled()) {
                    createNewAccount(firstName.getText().toString(),lastName.getText().toString(),eMail.getText().toString(),"industriell ekonomi",createUsername.getText().toString(),createPassword.getText().toString());

                }


            }


        });


    }



    // checks that every criteria for the account to be created is forfilled
    public boolean checkIfFilled() {

        String red = "#ba160c";

        if (!checkBoxGDPR.isChecked() || !checkBoxRules.isChecked() || createUsername.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()) {
            setColorText(red);
            Toast.makeText(CreateAccount.this, "Please fill in all the things that are red", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!checkPassword()) {
            setcolorPassword(red);
            Toast.makeText(CreateAccount.this, "The password does not match the criteria or the two passwords do not match", Toast.LENGTH_SHORT).show();


            return false;
        } else {
            return true;
        }

    }

    private boolean checkEducation() {
        int index = 0;

        for (CheckBox i : educationArray) {
            if (i.isChecked()) {
                index++;
            }
        }
        if (index == 1) {
            return true;
        } else
            return false;
    }

    //Sets the color of the textview and so on
    private void setColorText(String color) {

        firstName.setHintTextColor(Color.parseColor(color));
        lastName.setHintTextColor(Color.parseColor(color));
        createUsername.setHintTextColor(Color.parseColor(color));
        eMail.setHintTextColor(Color.parseColor(color));
        checkBoxGDPR.setTextColor(Color.parseColor(color));
        checkBoxRules.setTextColor(Color.parseColor(color));

    }
    //sets color to the password
    private void setcolorPassword(String color) {

        passwordText.setTextColor(Color.parseColor(color));
        passwordText2.setTextColor(Color.parseColor(color));
    }

    //checks if the password is 8 char long and if it contains atleast on capital letter and one digit
    public boolean checkPassword() {


        if (createPassword.getText().toString().length() >= 8) {
            boolean checknumber = false;
            boolean checkCapLetter = false;
            for (int i = 0; i < createPassword.getText().toString().length(); i++) {


                if (Character.isDigit(createPassword.getText().toString().charAt(i))) {
                    checknumber = true;
                }
                if (Character.isUpperCase(createPassword.getText().toString().charAt(i))) {
                    checkCapLetter = true;
                }
                if (checknumber && checkCapLetter) {
                    return true;
                }

            }
        }
        return false;
    }
    //Here we insert the values the user information into the database through a php code
    private void createNewAccount(final String firstName, final String lastName, final String email, final String education, final String username, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(CreateAccount.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Creating the Account");
        progressDialog.show();
        String url = "http://192.168.1.112/createAccount.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            //Here the method checks if the response from the php code is "Successfully created an account" that it should create
            public void onResponse(String response) {
                if (response.equals("Successfully created an account")) {
                    progressDialog.dismiss();
                    Toast.makeText(CreateAccount.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateAccount.this, MainActivity.class));
                }
                else{
                    //displays the respone that was received from the php code
                    progressDialog.dismiss();
                    Toast.makeText(CreateAccount.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(CreateAccount.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            //here is the variables that goes into the php code
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("firstname",firstName);
                param.put("lastname",lastName);
                param.put("email",email);
                param.put("education",education);
                param.put("username",username);
                param.put("password",password);


                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(CreateAccount.this).addToRequestQueue(request);
    }
}



