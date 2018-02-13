package com.example.makati.softengg_project;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class LandingFragment extends Fragment {

    ImageButton submit_multi;
    ImageButton submit_solo;
    Button score;

    private static boolean isMultiplayer = false;

    MediaPlayer btnSound;


    public LandingFragment() {


    }

    public static boolean passIsMultiplayer(){ return isMultiplayer;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_landing, container, false);

        submit_multi = rootView.findViewById(R.id.submit_multi);
        submit_solo = rootView.findViewById(R.id.submit_solo);

        btnSound = MediaPlayer.create(getActivity(), R.raw.buttonsound);
        score = rootView.findViewById(R.id.score);

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ScoreFragment scoreFragment;
                scoreFragment = new ScoreFragment();
                FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, scoreFragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });


        submit_solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isMultiplayer = false;
                btnSound.start();

                final ChooseTimeFragment chooseTimer;
                chooseTimer = new ChooseTimeFragment();
                FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chooseTimer);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });


        submit_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnSound.start();

                isMultiplayer = true;

                final ChooseTimeFragment chooseTimer;
                chooseTimer = new ChooseTimeFragment();
                FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chooseTimer);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        return  rootView;
    }
}
