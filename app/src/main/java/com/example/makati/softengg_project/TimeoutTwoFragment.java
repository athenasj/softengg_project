package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class TimeoutTwoFragment extends Fragment {

    ImageButton submit_breakdownTwo;

    public TimeoutTwoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_timeout_two, container, false);

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

        return rootView;
    }

}
