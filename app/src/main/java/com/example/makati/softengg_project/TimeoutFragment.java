package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class TimeoutFragment extends Fragment {

    ImageButton submit_prize;
    ImageButton submit_breakdown;
    TextView score_solo;
    TextView score_timerSolo;

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

        return rootView;

    }

}