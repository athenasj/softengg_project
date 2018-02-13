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

public class ResultOneFragment extends Fragment {

    public static ArrayList<String> finalAns_plOne = new ArrayList<String>();

    ListView ansListOne;
    ImageButton btn_toPrize, btn_home;
    int passedScoreOne = 0;

    TextView labelScore;

    public ResultOneFragment() {
        finalAns_plOne = PlayFragment.passStrAnswerOne();
        passedScoreOne = PlayFragment.passScoreOne();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_result_one, container, false);

        labelScore = rootView.findViewById(R.id.labelScore);
        ansListOne = rootView.findViewById(R.id.listOne);

        btn_toPrize = rootView.findViewById(R.id.btn_toPrize);
        btn_home = rootView.findViewById(R.id.btn_home);

        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, finalAns_plOne);
        ansListOne.setAdapter(adapterOne);

        labelScore.setText(String.valueOf(passedScoreOne));

        btn_toPrize.setOnClickListener(new View.OnClickListener() {
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

