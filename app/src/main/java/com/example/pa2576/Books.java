package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Books extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Integer> idArray = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();
    ArrayList<Integer> chapterArrayOne = new ArrayList<>();
    ArrayList<Integer> chapterArrayTwo = new ArrayList<>();

    int chosenBook;
    int nrOfBooks;
    String bookName;
    int number=1;
    String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        //Retrieve variables from other classes
        chosenBook = getIntent().getIntExtra("NAME_OF_BOOK",-1);
        nrOfBooks = getIntent().getIntExtra("NR_OF_BOOK",0);
        courseName = getIntent().getStringExtra("CHOSEN_COURSE");
        setTitle(courseName);
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








       //checkbook();
        getBooks(courseName);

        //Make the buttons clickable
        for (int i = 0; i < btnArray.size(); i++) {
            btnArray.get(i).setOnClickListener( this);
        }




    }
    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()){
            default:
               checkPressedBtnBook(v.getId());
                break;
        }
    }




    public void setTextBtnBooks(String[] books){

        for(int i=0; i<books.length;i++) {
            btnArray.get(i).setText(books[i]);
        }
        for(int i=0; i<btnArray.size();i++) {
            if(btnArray.get(i).getText().equals("")){
                btnArray.get(i).setVisibility(View.GONE);
            }

        }


    }
    //checks which button that is pressed
    public void checkPressedBtnBook(int id) {


            for(int i=0; i<btnArray.size();i++) {
                if (btnArray.get(i).getId() == id) {

                        bookName = (btnArray.get(i).getText().toString());
                    }
                }


                openChapters();
    }
    //opens the chapters class and sends som variables into that class
    public void openChapters() {

        Intent intent = new Intent(this, Chapter.class);
        intent.putExtra("BOOK_NAME", bookName);
        intent.putExtra("HEAD_NAVIGATOR", courseName + " -> " + bookName);
        startActivity(intent);
    }
    //Input value course into database and extract all the books on that course
    public void getBooks(final String course) {
        final ProgressDialog progressDialog = new ProgressDialog(Books.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Books");
        progressDialog.show();
        String url = "http://192.168.1.112/books.php";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                String[] books = btnArray.get(0).getText().toString().split(",");
                setTextBtnBooks(books);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Books.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("course", course);
                return param;

            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Books.this).addToRequestQueue(request);


    }
}