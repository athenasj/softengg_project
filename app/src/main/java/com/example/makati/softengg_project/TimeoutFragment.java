package com.example.makati.softengg_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class TimeoutFragment extends Fragment {

    ImageButton submit_prize;
    ImageButton submit_breakdown;
    ImageButton btn_home;
    TextView score_solo;
    TextView score_timerSolo;
    String score[] = new String [6];
    int actScore [] = new int [6];

    int finalSoloTimer = 0;
    int finalSoloScore = 0;

    public TimeoutFragment() {
        finalSoloTimer = ChooseTimeFragment.TimeSelected();
        finalSoloScore = PlayFragment.passScoreOne();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_timeout, container, false);


        submit_prize = rootView.findViewById(R.id.submit_prize);
        submit_breakdown = rootView.findViewById(R.id.submit_breakdown);
        score_solo = rootView.findViewById(R.id.score_solo);
        score_timerSolo = rootView.findViewById(R.id.score_timerSolo);

        score_timerSolo.setText(String.valueOf(finalSoloTimer));
        score_solo.setText(String.valueOf(finalSoloScore));

        if (finalSoloTimer == 60)
            forScoreboard(); //scoreboard only includes 60 second games




        btn_home = rootView.findViewById(R.id.btn_home);

        submit_breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOneFragment toShowResults = new ResultOneFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,  toShowResults);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        submit_prize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrizeFragment toPrize = new PrizeFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,  toPrize);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LandingFragment backLanding = new LandingFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,  backLanding);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return rootView;

    }

    private void forScoreboard() {
        Boolean scoreadded = false;
        int prevScore = 0;
        SharedPreferences prefValues = getActivity().getSharedPreferences("scores", Context.MODE_PRIVATE);
        SharedPreferences.Editor addScore = prefValues.edit();


        for(int x = 1; x < 6; x++)
        {
            try {

                //prevScore = Integer.parseInt(prefValues.getString("score" + String.valueOf(x + 1), "0"));//0
                score[x] = prefValues.getString("score" + String.valueOf(x), "0"); //19
                actScore[x] = Integer.parseInt(score[x]); //19

                if (finalSoloScore >= actScore[x]) {
                    if (!scoreadded) {
                        addScore.putString("score" + String.valueOf(x), String.valueOf(finalSoloScore));
                        prevScore = actScore[x];
                        addScore.apply();
                        scoreadded = true;
                    }
                    else{
                        addScore.putString("score" + String.valueOf(x),String.valueOf(prevScore));
                        prevScore = actScore[x];
                        addScore.apply();
                        Log.e("ARRANGESCORES", "prevscore: " + prevScore);
                    }
                }
                Log.e("ARRANGESCORES", String.valueOf(x) + ": " + score[x]);

            }catch (IndexOutOfBoundsException e){
                Log.e("ERROR","OUTOFBOUNDS");
            }

        }
/*
        for(int x = 5; x > 0; x--)
        {
            score[x] = prefValues.getString("score"+String.valueOf(x),"0");*//*
                actScore[x] = Integer.parseInt(score[x]);*//*
            Log.e("SCORES",String.valueOf(x)+": "+score[x]);
        }*/




    }

}