package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultOneFragment extends Fragment {

    public static ArrayList<String> finalAns_plOne = new ArrayList<String>();

    ListView ansListOne;
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

        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, finalAns_plOne);
        ansListOne.setAdapter(adapterOne);

        labelScore.setText(String.valueOf(passedScoreOne));
        return rootView;
    }
}

