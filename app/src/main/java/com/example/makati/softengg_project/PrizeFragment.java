package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class PrizeFragment extends Fragment {
    ImageButton btn_home, btn_shuffle;
    TextView text_prize;

    double randomNum;
    double randomNum2;
    int finalNum;

    String finalPrize;

    public PrizeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_prize, container, false);

        btn_home = rootView.findViewById(R.id.btn_home);
        text_prize = rootView.findViewById(R.id.text_prize);
        btn_shuffle = rootView.findViewById(R.id.btn_shuffle);

        text_prize.setText("Click Play to show a deal");

        randomNum = Math.random();
        randomNum2 = randomNum * 9;
        finalNum = (int)randomNum2;

        Log.e("finalNum", String.valueOf(finalNum));
        switch (finalNum){
            case 0:
                finalPrize = "Treat Winner with a 20 php worth of Merienda";
                break;
            case 1:
                finalPrize = "Except Winner with one chore";
                break;
            case 2:
                finalPrize = "Dare the Loser";
                break;
            case 3:
                finalPrize = "Ask the Loser a truth";
                break;
            case 4:
                finalPrize = "Give winner 10 php in cash";
                break;
            case 5:
                finalPrize = "Give winner 30 php in cash";
                break;
            case 6:
                finalPrize = "Treat Winner a mcdo sundae";
                break;
            case 7:
                finalPrize = "Treat Winner a jollibee food";
                break;
            case 8:
                finalPrize = "Loser will pay Winner's lunch";
                break;
            case 9:
                finalPrize = "Give winner 1 peso";
                break;
        }


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

        btn_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            text_prize.setText(finalPrize);

            }
        });


        return rootView;
    }

}
