package com.example.makati.softengg_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ScoreFragment extends Fragment {

    TextView Score1, Score2, Score3, Score4, Score5;
    String score[] = new String [6];
    Button clearScore;
    SharedPreferences prefValues;

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        Score1 = rootView.findViewById(R.id.Score1);
        Score2 = rootView.findViewById(R.id.Score2);
        Score3 = rootView.findViewById(R.id.Score3);
        Score4 = rootView.findViewById(R.id.Score4);
        Score5 = rootView.findViewById(R.id.Score5);
        clearScore = rootView.findViewById(R.id.clearScore);


        prefValues = getActivity().getSharedPreferences("scores", Context.MODE_PRIVATE);
        //SharedPreferences.Editor addScore = prefValues.edit();
        //addScore.clear();
        //addScore.apply();


        checkScores();

        clearScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor addScore = prefValues.edit();
                addScore.clear();
                addScore.apply();
                checkScores();
            }
        });


        return rootView;
    }

    private void checkScores() {
        for(int x = 5; x > 0; x--)
            {
                score[x] = prefValues.getString("score"+String.valueOf(x),"0");/*
                actScore[x] = Integer.parseInt(score[x]);*/
                Log.e("SCORES",String.valueOf(x)+": "+score[x]);
            }

        Score1.setText("1. " + score[1]);
        Score2.setText("2. " + score[2]);
        Score3.setText("3. " + score[3]);
        Score4.setText("4. " + score[4]);
        Score5.setText("5. " + score[5]);
    }
}
