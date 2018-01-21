package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultOneFragment extends Fragment {

    public static ArrayList<String> finalAns_plOne = new ArrayList<String>();
    ListView ansListOne;

    public ResultOneFragment() {
        finalAns_plOne = PlayFragment.passStrAnswerOne();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_result_one, container, false);

        ansListOne = rootView.findViewById(R.id.listOne);
        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, finalAns_plOne);
        ansListOne.setAdapter(adapterOne);

        return rootView;
    }
}

