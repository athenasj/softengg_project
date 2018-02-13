package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ResultTwoFragment extends Fragment {

    ListView ansListTwo;
    TextView labelScoreTwo;
    ImageButton submit_prize;
    ImageButton btn_home;
    String labelScore;

    public static ArrayList<String> finalAns_pTwo = new ArrayList<String>();

    public ResultTwoFragment() {
        finalAns_pTwo = PlayFragment.passStrAnswerTwo();
        labelScore = TimeoutTwoFragment.getResults();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_result_two, container, false);

        submit_prize = rootView.findViewById(R.id.submit_prize);
        labelScoreTwo = rootView.findViewById(R.id.labelScoreTwo);
        labelScoreTwo.setText(labelScore);

        ansListTwo = rootView.findViewById(R.id.listTwo);
        btn_home = rootView.findViewById(R.id.btn_home);

        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, finalAns_pTwo);
        ansListTwo.setAdapter(adapterOne);

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
