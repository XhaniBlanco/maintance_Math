package com.example.pa2576;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> idArray = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();


    ArrayList<Integer> taskArray = new ArrayList<>();


    int chapterNR;
    int nrOfTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        chapterNR = getIntent().getIntExtra("CHAPTER_NR",0);
        nrOfTasks = getIntent().getIntExtra("NR_OF_TASKS_IN_CHAPTER",0);
        setTitle(getIntent().getStringExtra("CHOSEN_BOOK"));
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


        for (int id : idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);


        }
        for (int i = 0; i <nrOfTasks ; i++) {
            taskArray.add(i);
        }
        setTextBtn();
        for (int i = 0; i < taskArray.size(); i++) {
            btnArray.get(i).setOnClickListener(this);

        }


    }

    public void setTextBtn() {

        for (int i = 0; i <taskArray.size() ; i++) {
            btnArray.get(i).setText("Task " + chapterNR +"." +(i+1)+"");

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




    private void checkPressedBtn(int id) {


        for (int i = 0; i < nrOfTasks; i++) {
            if (btnArray.get(i).getId() == id) {
                //
            }
        }
    }


}


