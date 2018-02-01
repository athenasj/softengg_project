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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PlayFragment extends Fragment {

    TextView timetext, textview_firstdigit, textview_seconddigit ;
    TextView item_counter, textOperand ;
    ImageButton btn_nextproblem ;
    EditText answer_field ;

    int selectedTime = 0 ;
    int finalTime = 0 ;
    int answer = 0 ;
    int counter = 0 ;
    boolean nextquestion = true ;
    boolean passedIsMulti = false;
    boolean isP1Done;
    String strContainer = " " ;
    String mark = " " ;

    String passedOperation = " " ;

    int[] givenFirstDigits = new int[100] ;
    int[] givenSecondDigits = new int[100] ;
    int[] givenPlusAns = new int[100] ;
    int[] givenSubAns = new int[100] ;

    int[] finalAns = new int[100] ;



    public static ArrayList<Integer> ans_playerone = new ArrayList<Integer>(); //Player's One
    public static ArrayList<String> strAnsOne = new ArrayList<String>(); // Shows the question and user's answer on a ListView

    private static int scorePlayerOne = 0;
    private static boolean p1FinallyDone;

    public static ArrayList<String> passStrAnswerOne(){ return strAnsOne; }
    public static int passScoreOne(){ return scorePlayerOne; }
    public static boolean getP1Done(){ return p1FinallyDone; }
    CountDownTimer countitdown;
    Boolean homePress = false;

    public PlayFragment() {
        // Required empty public constructor
        selectedTime = ChooseTimeFragment.TimeSelected();
        givenFirstDigits = ChooseTimeFragment.getFirstDigits();
        givenSecondDigits = ChooseTimeFragment.getSecondDigits();
        passedOperation = ChooseTimeFragment.passOperation();
        givenPlusAns = ChooseTimeFragment.getPlusAnswers();
        givenSubAns = ChooseTimeFragment.getSubAnswers();
        passedIsMulti = LandingFragment.passIsMultiplayer();
        isP1Done = ChooseTimeFragment.getP1Status();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_play, container, false);

        p1FinallyDone = ReadyFragment.player1Stat();
        Log.e("Play Fragment","isPlayer1Done? " + p1FinallyDone);
        timetext = rootView.findViewById(R.id.timetext);
        textview_firstdigit = rootView.findViewById(R.id.textview_firstdigit);
        textview_seconddigit = rootView.findViewById(R.id.textview_seconddigit);
        btn_nextproblem = rootView.findViewById(R.id.btn_nextproblem);
        answer_field = rootView.findViewById(R.id.answer_field);
        item_counter = rootView.findViewById(R.id.item_counter);
        textOperand = rootView.findViewById(R.id.textOperand);


        finalTime = selectedTime * 1000;

        //finalTime = 3000; //comment later
        countitdown = new CountDownTimer(finalTime,200){
            public void onTick(long millisUntilFinished){
                timetext.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                    try{
                        if (passedIsMulti) {

                            if (p1FinallyDone) {
                                TimeoutFragment toTimeOut = new TimeoutFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_container, toTimeOut);
                                transaction.commit();
                            } else {
                                p1FinallyDone = true;

                                ReadyFragment toSecondReadyFrag = new ReadyFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_container, toSecondReadyFrag);
                                transaction.commit();
                            }
                        } else {

                                TimeoutFragment toTimeOut = new TimeoutFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_container, toTimeOut);
                                /*transaction.addToBackStack(null);*/
                                transaction.commit();
                            }

                }catch (Exception e) {
                    Log.e("Error", e.toString());
                    homePress = true;
                }
            }
        }.start();

        settingOperation();

        textview_firstdigit.setText(String.valueOf(givenFirstDigits[counter]));
        textview_seconddigit.setText(String.valueOf(givenSecondDigits[counter]));
        item_counter.setText(String.valueOf(counter+1));

        btn_nextproblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        answer_field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        nextQuestion();
                    }
                    return false;
            }
        });


        //Back - Player will be unable to go back while playing
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("Backpress", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("Backpress", "onKey Back listener is working!!!");
                    //getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });




        return rootView;

    }

    private void settingOperation() {
        textOperand.setText(passedOperation);
        if(passedOperation.equals("+")){
            finalAns = givenPlusAns;
        }
        else{
            finalAns = givenSubAns;
        }
    }

    private void nextQuestion() {
        try{
            answer = Integer.parseInt(answer_field.getText().toString());
            nextquestion = true;
            answer_field.setText("");

            if(nextquestion){
                if(finalAns[counter] == answer){
                    mark = "/";
                    scorePlayerOne++;
                }
                else{
                    mark = "x";
                }
                strContainer = String.valueOf(givenSecondDigits[counter]) + " " + passedOperation + " " + String.valueOf(givenFirstDigits[counter] + " = " + String.valueOf(answer) + " Correct Answer: " + String.valueOf(finalAns[counter]) + "   " + mark);
                counter++;
                ans_playerone.add(answer);
                strAnsOne.add(strContainer);
                item_counter.setText(String.valueOf(counter+1));
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


    //When home is pressed and app is reopened
    @Override
    public void onResume() {
        super.onResume();
        if (homePress)
        {
            ReadyFragment fragment = new ReadyFragment(); //this block set which fragment should load initially
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment); //use fragment_container to show fragments/pages
            fragmentTransaction.commit();
        }
    }


}
