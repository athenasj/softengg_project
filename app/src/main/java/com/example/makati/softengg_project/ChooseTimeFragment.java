package com.example.makati.softengg_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ChooseTimeFragment extends Fragment {

    RadioGroup timer_group;
    RadioButton selected_timer;
    ImageButton submit_sub;
    ImageButton submit_add;

    private static String operation;
    private static int timer = 30;
    private static boolean isP1Done = false;

    private static int[] firstdigits = new int[100];
    private static int[] seconddigits = new int[100];
    private static int[] answersPlus = new int[100];
    private static int[] answersSub = new int[100];

    double randNum1, randNum2;
    double rNum1, rNum2;


    public  ChooseTimeFragment() {
        // Required empty public constructor
        for(int i = 0; i < firstdigits.length; i++){
            randNum1 = Math.random();
            rNum1 = randNum1 * 100;
            firstdigits[i] = (int)rNum1;

            randNum2 = Math.random();
            rNum2 = randNum2 * 100;
            seconddigits[i] = (int)rNum2;

            answersPlus[i] = firstdigits[i] + seconddigits[i];
            answersSub[i] = firstdigits[i] - seconddigits[i];
        }
    }

    public static int TimeSelected(){
        return timer;
    }
    public static int[] getFirstDigits() { return firstdigits; }
    public static int[] getSecondDigits() { return seconddigits; }
    public static int[] getPlusAnswers() { return answersPlus; }
    public static int[] getSubAnswers() { return answersSub; }
    public static String passOperation(){ return operation;}
    public static boolean getP1Status(){ return isP1Done;}
    public static boolean fromChooseTime(){ return true;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_choose_time, container, false);

        timer_group = rootView.findViewById(R.id.timer_group);
        submit_sub = rootView.findViewById(R.id.submit_sub);
        submit_add = rootView.findViewById(R.id.submit_add);

        submit_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "-";
                settingTimer(rootView);
            }
        });

        submit_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "+";
                settingTimer(rootView);
            }
        });

        return rootView;
    }

    private void settingTimer(View rootView) {
        int selectedTime = timer_group.getCheckedRadioButtonId();
        selected_timer = rootView .findViewById(selectedTime);
        String selection = selected_timer.getText().toString();

        if(selection.equalsIgnoreCase("30 SECONDS") ){
            timer = 30;
        }
        else if(selection.equalsIgnoreCase("45 SECONDS")){
            timer = 45;
        }
        else if(selection.equalsIgnoreCase("60 SECONDS")){
            timer = 60;
        }
        else {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
        ReadyFragment toReadyPage = new ReadyFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,  toReadyPage);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
