package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity implements View.OnClickListener {
    Button math, physics, info, profile;
    TextView welcome, searchinfo;
    SearchView search;
    String subject=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        math = (Button) findViewById(R.id.mathBtn);
        physics = (Button) findViewById(R.id.physicsBtn);
        info = (Button) findViewById(R.id.infoBtn);
        profile = (Button) findViewById(R.id.profileBtn);

        math.setOnClickListener( this);
        physics.setOnClickListener( this);
        info.setOnClickListener( this);
        profile.setOnClickListener( this);
}

    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mathBtn:
                subject = "Math";
                openCourses();
                break;

            case R.id.physicsBtn:
                subject = "Physics";
                openCourses();
                break;
            case R.id.infoBtn:
                openInfo();
                break;
            case R.id.profileBtn:
                openProfile();
                break;
        }
    }

    public void openProfile() {
        Intent profile = new Intent(this, ProfilePage.class);
        startActivity(profile);
    }

    public void openCourses(){
        Intent course = new Intent(this, Courses.class);
        course.putExtra("SUBJECT",subject);
        startActivity(course);
    }
    public void openInfo() {

    }




}
