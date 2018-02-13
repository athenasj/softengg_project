package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class ReadyFragment extends Fragment {

    ImageButton btn_start;

    private static boolean isP1Done;
    boolean isP1ReallyDone = false;

    public static boolean player1Stat(){
        return isP1Done;
    }


    public  ReadyFragment() {
        isP1Done = ChooseTimeFragment.getP1Status();
        isP1ReallyDone = PlayFragment.getP1Done();
        isP1Done = isP1ReallyDone;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_ready, container, false);


        btn_start = rootView.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PlayFragment toPlayPage = new PlayFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,  toPlayPage);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        Log.e("Ready Fragment","isPlayer1Done? " + isP1Done);

        return rootView;
    }

}
