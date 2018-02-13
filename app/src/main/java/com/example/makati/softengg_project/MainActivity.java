package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LandingFragment fragment = new LandingFragment(); //this block set which fragment should load initially
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment); //use fragment_container to show fragments/pages
        fragmentTransaction.commit();
    }

    public void onSelectFragment(View v) { //this function of code switches between fragments by using button ids

    }



}
