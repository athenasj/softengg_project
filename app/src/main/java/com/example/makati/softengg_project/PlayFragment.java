package com.example.makati.softengg_project;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PlayFragment extends Fragment {

    TextView timetext, textview_firstdigit, textview_seconddigit;
    int selectedTime;
    int finalTime;
    Button btn_nextproblem;
    EditText answer_field;
    TextView item_counter, textOperand;
    int answer;
    int counter = 0;
    boolean nextquestion = true;

    String passedOperation;

    int[] givenFirstDigits = new int[100];
    int[] givenSecondDigits = new int[100];
    int[] givenPlusAns = new int[100];
    int[] givenSubAns = new int[100];
    int[] finalAns = new int[100];


    public static ArrayList<Integer> ans_playerone = new ArrayList<Integer>(); //Player's One

    public static ArrayList<String> strAnsOne = new ArrayList<String>(); // Shows the question and user's answer on a ListView

    String strContainer;


    public static ArrayList<String> passStrAnswerOne(){ return strAnsOne; }

    public PlayFragment() {
        // Required empty public constructor
        selectedTime = ChooseTimeFragment.TimeSelected();
        givenFirstDigits = ChooseTimeFragment.getFirstDigits();
        givenSecondDigits = ChooseTimeFragment.getSecondDigits();
        passedOperation = ChooseTimeFragment.passOperation();
        givenPlusAns = ChooseTimeFragment.getPlusAnswers();
        givenSubAns = ChooseTimeFragment.getSubAnswers();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View rootView = inflater.inflate(R.layout.fragment_play, container, false);

        timetext = rootView.findViewById(R.id.timetext);
        textview_firstdigit = rootView.findViewById(R.id.textview_firstdigit);
        textview_seconddigit = rootView.findViewById(R.id.textview_seconddigit);
        btn_nextproblem = rootView.findViewById(R.id.btn_nextproblem);
        answer_field = rootView.findViewById(R.id.answer_field);
        item_counter = rootView.findViewById(R.id.item_counter);
        textOperand = rootView.findViewById(R.id.textOperand);


        finalTime = selectedTime * 1000;
        new CountDownTimer(finalTime,200){
            public void onTick(long millisUntilFinished){
                timetext.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                TimeoutFragment toTimeOut = new TimeoutFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,  toTimeOut);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }.start();

        textOperand.setText(passedOperation);
        if(passedOperation.equals("+")){
            finalAns = givenPlusAns;
        }
        else{
            finalAns = givenSubAns;
        }

        textview_firstdigit.setText(String.valueOf(givenFirstDigits[counter]));
        textview_seconddigit.setText(String.valueOf(givenSecondDigits[counter]));
        item_counter.setText(String.valueOf(counter+1));

        btn_nextproblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    answer = Integer.parseInt(answer_field.getText().toString());
                    nextquestion = true;
                    answer_field.setText("");
                    Log.e("OUTSIDE NEXT QUESTION","hello play frag");
                    if(nextquestion){
                        strContainer = String.valueOf(givenSecondDigits[counter]) + " - " + String.valueOf(givenFirstDigits[counter] + " = " + String.valueOf(answer) + " Correct Answer: " + String.valueOf(finalAns[counter]));
                        counter++;
                        ans_playerone.add(answer);
                        strAnsOne.add(strContainer);
                        item_counter.setText(String.valueOf(counter+1));
                    }
                    textview_firstdigit.setText(String.valueOf(givenFirstDigits[counter]));
                    textview_seconddigit.setText(String.valueOf(givenSecondDigits[counter]));


                }catch(NumberFormatException e){
                    Toast.makeText(getActivity(), "INVALID ANSWER!" , Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
                    nextquestion = false;
                }
            }
        });

        answer_field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        try{
                            answer = Integer.parseInt(answer_field.getText().toString());
                            ans_playerone.add(answer);
                            nextquestion = true;
                            answer_field.setText("");
                            if(nextquestion){
                                strContainer = String.valueOf(givenSecondDigits[counter]) + " - " + String.valueOf(givenFirstDigits[counter] + " = " + String.valueOf(answer) + " Correct Answer: " + String.valueOf(finalAns[counter]));
                                counter++;
                                ans_playerone.add(answer);
                                strAnsOne.add(strContainer);
                                item_counter.setText(String.valueOf(counter+1));
//                                Log.e("IN NEXT QUESTION",strContainer);
                            }
                            textview_firstdigit.setText(String.valueOf(givenFirstDigits[counter]));
                            textview_seconddigit.setText(String.valueOf(givenSecondDigits[counter]));

                        }catch(NumberFormatException e){
                            Toast toast = Toast.makeText(getActivity(), "INVALID ANSWER!" , Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
                            toast.show();
                            nextquestion = false;
                        }
                    }
                    return false;
            }
        });

//        for(int l = 0; l < givenFirstDigits.length; l++){
//            Toast.makeText(getActivity(), String.valueOf(givenFirstDigits[l]) + " + " + String.valueOf(givenSecondDigits[l]) , Toast.LENGTH_SHORT).show();
//            problemtext.setText(String.valueOf(givenFirstDigits[l]) + " + " + String.valueOf(givenSecondDigits[l]));
//        }

        return rootView;

    }


}
