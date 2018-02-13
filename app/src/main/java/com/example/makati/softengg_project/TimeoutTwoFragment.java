package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class TimeoutTwoFragment extends Fragment {

    ImageButton submit_breakdownTwo;
    ImageButton submit_prize;
    ImageButton btn_home;
    TextView score_timerMulti, score_multi;
    int timeChosen = 0, scoreOne = 0, scoreTwo = 0;
    private static String compareScores;

    public static String getResults(){ return compareScores; }

    public TimeoutTwoFragment() {
        timeChosen = ChooseTimeFragment.TimeSelected();
        scoreOne = PlayFragment.passScoreOne();
        scoreTwo = PlayFragment.passScoreTwo();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_timeout_two, container, false);

        score_timerMulti = rootView.findViewById(R.id.score_timerMulti);
        score_multi = rootView.findViewById(R.id.score_multi);
        submit_prize = rootView.findViewById(R.id.submit_prize);
        btn_home = rootView.findViewById(R.id.btn_home);

        submit_breakdownTwo = rootView.findViewById(R.id.submit_breakdownTwo);
        submit_breakdownTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResultTwoFragment toShowResultsTwo = new ResultTwoFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,  toShowResultsTwo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        compareScores = String.valueOf(scoreOne) + " : " + String.valueOf(scoreTwo);
        score_multi.setText(compareScores);
        score_timerMulti.setText(String.valueOf(timeChosen));

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

}
