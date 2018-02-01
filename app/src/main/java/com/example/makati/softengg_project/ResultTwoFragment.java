package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ResultTwoFragment extends Fragment {

    ListView ansListTwo;
    public static ArrayList<String> finalAns_pTwo = new ArrayList<String>();

    public ResultTwoFragment() {
        finalAns_pTwo = PlayFragment.passStrAnswerTwo();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_result_two, container, false);

        ansListTwo = rootView.findViewById(R.id.listTwo);

        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, finalAns_pTwo);
        ansListTwo.setAdapter(adapterOne);
        return rootView;
    }

}
