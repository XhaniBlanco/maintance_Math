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

public class Chapter extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> idArray = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();


    String bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        bookName = getIntent().getStringExtra("BOOK_NAME");

        setTitle(getIntent().getStringExtra("HEAD_NAVIGATOR"));
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

        //fill the array with buttons
        for (int id : idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);


        }

        //makes all the buttons clickable
        for (int i = 0; i < btnArray.size(); i++) {
            btnArray.get(i).setOnClickListener(this);

        }

        getChapters(bookName);

    }
    //sets the texts of all the buttons
    public void setTextBtn(String nrOfChapters) {
        int temp = Integer.parseInt(nrOfChapters);
        for (int i = 0; i <temp ; i++) {

            btnArray.get(i).setText("Chapter " +(i+1)+"");

        }
        for(int i=0; i<btnArray.size();i++) {
            if (btnArray.get(i).getText().equals("")) {
                btnArray.get(i).setVisibility(View.GONE);
            }
        }


    }

    public void onClick(View v) {
        switch (v.getId()) {
            default:
                checkPressedBtn(v.getId());
                break;
        }
    }



    //checks which button that is pressed and opens task.class and send som variables to the task class
    private void checkPressedBtn(int id) {


        for (int i = 0; i < btnArray.size(); i++) {
            if (btnArray.get(i).getId() == id) {
                Intent intent = new Intent(this, Tasks.class);
                intent.putExtra("CHAPTER_NR", (i+1));
                intent.putExtra("BOOK_NAME",bookName);
                intent.putExtra("CHOSEN_BOOK",getIntent().getStringExtra("CHOSEN_BOOK") + " -> Cap " +(i+1) + "");
                startActivity(intent);
            }
        }
    }
    //Input value book into database and extract how many chapters that book has
    public void getChapters(final String book) {
        final ProgressDialog progressDialog = new ProgressDialog(Chapter.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Books");
        progressDialog.show();
        String url = "http://192.168.1.112/chapters.php";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                setTextBtn(btnArray.get(0).getText().toString());
                Toast.makeText(Chapter.this,bookName, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Chapter.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("book", book);
                return param;

            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Chapter.this).addToRequestQueue(request);


    }
}



