package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.CornerPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Courses extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Integer> idArray = new ArrayList<>();

    ArrayList<Button> btnArray = new ArrayList<>();


   // final ArrayList<String> mathCourses = new ArrayList<>();
    ArrayList<Integer> mathNrBooks= new ArrayList<>();
    ArrayList<String> physicsCourses = new ArrayList<>();

    //If the index is 0 the mathcourses will be displayed and if it is 1 the physichscourses will be displayed
    int index;
    String courseName;
    String bookName;
    int nrOfBooks;
    String subject;
    public static String coursesPHP = "test2131PHP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        subject = getIntent().getStringExtra("SUBJECT");
        idArray.add(R.id.button1);
        idArray.add(R.id.button2);
        idArray.add(R.id.button3);
        idArray.add(R.id.button4);
        idArray.add(R.id.button5);
        idArray.add(R.id.button6);
        idArray.add(R.id.button7);
        idArray.add(R.id.button8);
        idArray.add(R.id.button9);
        idArray.add(R.id.button10);
        idArray.add(R.id.button11);
        idArray.add(R.id.button12);
        idArray.add(R.id.button13);

        //Adds the buttons in the btnArray
        for (int id: idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);
        }


        getCourses(subject);


        //Make the buttons clickable
        for (int i = 0; i < btnArray.size(); i++) {
            btnArray.get(i).setOnClickListener( this);
        }


    }



    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()){
            default:
                    checkPressedBtnMath(v.getId());

                break;
        }
    }


    //checks which button that is pressed when the subjects it maths
    public void checkPressedBtnMath(int id) {


        for (int i = 0; i <btnArray.size() ; i++) {
            if(btnArray.get(i).getId() == id){
               // bookName = btnArray.get(i).getText().toString() ;
                //nrOfBooks = mathNrBooks.get(i);
                courseName = btnArray.get(i).getText().toString();
                openBooks();
            }
        }

    }
    // Opens the class books and send som variables through
    public void openBooks() {

        Intent intent = new Intent(this, Books.class);
        intent.putExtra("CHOSEN_COURSE", courseName);
        startActivity(intent);
    }

    //sets the texts on each button and make the buttons without any text invisible
    public void setTextBtnCourses(String[] courses){


        for(int i=0; i<courses.length;i++) {
            btnArray.get(i).setText(courses[i]);

        }
        for(int i=0; i<btnArray.size();i++) {
            if(btnArray.get(i).getText().equals("")){
                btnArray.get(i).setVisibility(View.GONE);
            }

        }

    }

    //Input value Subject into database and extract all the courses with that subject
    public void getCourses(final String subject) {
        final ProgressDialog progressDialog = new ProgressDialog(Courses.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Courses");
        progressDialog.show();
        String url = "http://192.168.1.112/courses.php";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                String[] courses = btnArray.get(0).getText().toString().split(",");
                setTextBtnCourses(courses);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Courses.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("subject", subject);
                return param;

            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Courses.this).addToRequestQueue(request);


    }

}