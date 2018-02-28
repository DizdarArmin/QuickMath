package com.app.dizdarious.quickmath;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dizdar on 2018-02-22.
 */

public class QuizActivity extends AppCompatActivity {
    TextView firstNumber; // operator
    TextView secondNumber; // operator
    TextView result;
    TextView operator;

    MediaPlayer player1;
    MediaPlayer player2;
    MediaPlayer player3;
    MediaPlayer player4;
    MediaPlayer player5;
    MediaPlayer player6;
    MediaPlayer player7;
    MediaPlayer player8;
    MediaPlayer player9;

    ProgressBar progressBar;



    Button firstRandom;
    Button secondRandom;
    Button thirdRandom;


    int additionUp = 1;



    int subtractionUp = 1;
    int multiplicationUp = 1;
    int divisionUp = 1;

    int additionLevel;
    int subtractionLevel;
    int multiplicationLevel;
    int divisionLevel;


    int additionRange = 10;
    int subtractionRange = 10;
    int multiplicationRange = 10;
    int divisionRange = 10;


    int additionLevelCap = 100;
    int subtractionLevelCap = 100;
    int multiplicationLevelCap = 100;
    int divisionLevelCap = 100;


    TextView additionLevelView;
    TextView subtractionLevelView;
    TextView multiplicationLevelView;
    TextView divisionLevelView;


    int generalResult;
    int clickCount;

    //COLORS
    static int lightRed;
    static int darkerRed;
    static int darkestRed;

    static int lightBlue;
    static int darkerBlue;
    static int darkestBlue;

    static int lightLime;
    static int darkerLime;
    static int darkestLime;

    static int lightPink;
    static int darkerPink;
    static int darkestPink;

    static int lightPurple;
    static int darkerPurple;
    static int darkestPurple;

    static int lightYellow;
    static int darkerYellow;
    static int darkestYellow;

    static int transparent;

    String decision;


    LinearLayout equation_holder;
    LinearLayout number_holder;
    ImageView kids_holder;

    LinearLayout toAddition;
    LinearLayout toSubtraction;
    LinearLayout toMultiplication;
    LinearLayout toDivision;




    ArrayList <Boolean> fiveInRow;


    ConstraintLayout fatherLayout;

    MathOperator mathOperator;


    boolean levelChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        player1 = new MediaPlayer();
        player2 = new MediaPlayer();
        player3 = new MediaPlayer();
        player4 = new MediaPlayer();
        player5 = new MediaPlayer();
        player6 = new MediaPlayer();
        player7 = new MediaPlayer();
        player8 = new MediaPlayer();
        player9 = new MediaPlayer();

        initializeSounds();



        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        mathOperator = new MathOperator();

        progressBar = findViewById(R.id.progress_bar);

        equation_holder = findViewById(R.id.equation_holder);
        number_holder = findViewById(R.id.number_holder);
        fatherLayout = findViewById(R.id.father);
        kids_holder= findViewById(R.id.kids_holder);

        transparent = ResourcesCompat.getColor(getResources(), R.color.transparent, null);

        lightRed = ResourcesCompat.getColor(getResources(), R.color.lightRed, null);
        darkerRed = ResourcesCompat.getColor(getResources(), R.color.darkerRed, null);
        darkestRed = ResourcesCompat.getColor(getResources(), R.color.darkestRed, null);

        lightBlue = ResourcesCompat.getColor(getResources(), R.color.lightBlue, null);
        darkerBlue = ResourcesCompat.getColor(getResources(), R.color.darkerBlue, null);
        darkestBlue = ResourcesCompat.getColor(getResources(), R.color.darkestBlue, null);

        lightLime = ResourcesCompat.getColor(getResources(), R.color.lightLime, null);
        darkerLime = ResourcesCompat.getColor(getResources(), R.color.darkerLime, null);
        darkestLime = ResourcesCompat.getColor(getResources(), R.color.darkestLime, null);

        lightPink = ResourcesCompat.getColor(getResources(), R.color.lightPink, null);
        darkerPink = ResourcesCompat.getColor(getResources(), R.color.darkerPink, null);
        darkestPink = ResourcesCompat.getColor(getResources(), R.color.darkestPink, null);

        lightPurple = ResourcesCompat.getColor(getResources(), R.color.lightPurple, null);
        darkerPurple = ResourcesCompat.getColor(getResources(), R.color.darkerPurple, null);
        darkestPurple = ResourcesCompat.getColor(getResources(), R.color.darkestPurple, null);

        lightYellow = ResourcesCompat.getColor(getResources(), R.color.lightYellow, null);
        darkerYellow = ResourcesCompat.getColor(getResources(), R.color.darkerYellow, null);
        darkestYellow = ResourcesCompat.getColor(getResources(), R.color.darkestYellow, null);





        fiveInRow = new ArrayList<>(4);


        toAddition = findViewById(R.id.toAddition);
        toSubtraction = findViewById(R.id.toSubtraction);
        toMultiplication = findViewById(R.id.toMultiplication);
        toDivision = findViewById(R.id.toDivision);




        Intent intent = getIntent();
        decision = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);


        additionLevelView = findViewById(R.id.addition_level);
        subtractionLevelView = findViewById(R.id.subtraction_level);
        multiplicationLevelView = findViewById(R.id.multiplication_level);
        divisionLevelView = findViewById(R.id.division_level);


        firstNumber = findViewById(R.id.first_number);
        operator = findViewById(R.id.operator);
        secondNumber = findViewById(R.id.second_number);
        result = findViewById(R.id.result);

        firstRandom = findViewById(R.id.first_random);
        secondRandom = findViewById(R.id.second_random);
        thirdRandom = findViewById(R.id.third_random);

        clickCount = 1;




            setTheme();
            loadLevel();
            levelUpdates();
            levelProgressBarUpdate();
            displayLevels();


        //additionLevel = 0;



    } // End of onCreate

    public void setTheme() {
        levelUpdates();
        if (decision.equals("addition")) {
            colorThemeBlue();
            kids_holder.setImageResource(R.drawable.kid_one);
            operator.setText("+");
            mathOperator.addition(additionRange);

            loadLevel();
            levelUpdates();
            levelProgressBarUpdate();


            additionView();
            displayLevels();
            randomize();

        }
        else if (decision.equals("subtraction")){
            colorThemePink();
            kids_holder.setImageResource(R.drawable.kid_four);
            mathOperator.subtraction(subtractionRange);
            operator.setText("-");

            loadLevel();
            levelUpdates();
            levelProgressBarUpdate();



            subtractionView();
            displayLevels();
            randomize();
        }
        else if (decision.equals("multiplication")){
            colorThemePurple();
            kids_holder.setImageResource(R.drawable.kid_tree);
            mathOperator.multiplication(multiplicationRange);
            operator.setText("*");

            loadLevel();
            levelUpdates();
            levelProgressBarUpdate();


            multiplicationView();
            displayLevels();
            randomize();
        }
        else if (decision.equals("division")){
            kids_holder.setImageResource(R.drawable.kid_two);
            colorThemeYellow();
            operator.setText("/");
            mathOperator.division(divisionRange);


            loadLevel();
            levelUpdates();
            levelProgressBarUpdate();


            divisionView();
            displayLevels();
            randomize();
        }



    }

    public void saveLevel(){

        SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();
        editor.putInt("addition", additionUp);
        editor.putInt("addition_exp", additionLevel);
        editor.putInt("addition_level_cap", additionLevelCap);

        editor.putInt("subtraction", subtractionUp);
        editor.putInt("subtraction_exp" , subtractionLevel);
        editor.putInt("subtraction_level_cap", subtractionLevelCap);

        editor.putInt("multiplication" , multiplicationUp);
        editor.putInt("multiplication_exp", multiplicationLevel);
        editor.putInt("multiplication_level_cap", multiplicationLevelCap);

        editor.putInt("division" , divisionUp);
        editor.putInt("division_exp", divisionLevel);
        editor.putInt("division_level_cap", divisionLevelCap);
        editor.commit();

    }

    public void loadLevel(){

        SharedPreferences prefs = getSharedPreferences("LEVEL", MODE_PRIVATE);

        additionUp = prefs.getInt("addition", -1);
        additionLevel = prefs.getInt("addition_exp", -1);
        additionLevelCap = prefs.getInt("addition_level_cap", -1);

        subtractionUp = prefs.getInt("subtraction", -1);
        subtractionLevel = prefs.getInt("subtraction_exp", -1);
        subtractionLevelCap = prefs.getInt("subtraction_level_cap", -1);

        multiplicationUp = prefs.getInt("multiplication", -1);
        multiplicationLevel = prefs.getInt("multiplication_exp", -1);
        multiplicationLevelCap = prefs.getInt("multiplication_level_cap", -1);

        divisionUp = prefs.getInt("division", -1);
        divisionLevel = prefs.getInt("division_exp", -1);
        divisionLevelCap = prefs.getInt("division_level_cap", -1);

    }



    public void playFile(){
        player1.start();


    }

    public void displayLevels(){
        additionLevelView.setText(additionToString());
        subtractionLevelView.setText(subtractionToString());
        multiplicationLevelView.setText(multiplicationToString());
        divisionLevelView.setText(divisionToString());
    }

    public String additionToString(){
            return  "LVL " + additionUp;
    }
    public String subtractionToString(){
        return "LVL " + subtractionUp;
    }
    public String multiplicationToString(){
            return "LVL " + multiplicationUp;
    }
    public String divisionToString(){
        return "LVL " + divisionUp;
    }

    public void notifyUser (){
       if (decision.equals("addition")){
        levelUpNotify(levelUpMessage(additionUp, "addition"));
           playFile();
       }

       else if (decision.equals("subtraction")){
        levelUpNotify(levelUpMessage(subtractionUp, "subtraction"));

       }
       else if (decision.equals("multiplication")) {
           levelUpNotify(levelUpMessage(multiplicationUp, "multiplication"));
       }
       else if (decision.equals("division")) {
           levelUpNotify(levelUpMessage(divisionUp, "division"));
       }
    }

    public void additionLevelUpdates(){
        if (additionLevel >= additionLevelCap){
            additionLevel = 0;

            additionUp++;
            notifyUser();
            additionLevelCap = additionLevelCap + additionLevelCap;
            additionRange = additionRange + additionRange;
        }
    }
    public void subtractionLevelUpdates() {
        if (subtractionLevel >= subtractionLevelCap){
            subtractionLevel = 0;
            subtractionUp++;
            notifyUser();


            subtractionLevelCap = subtractionLevelCap + subtractionLevelCap;
            subtractionRange = subtractionRange  +  subtractionRange;
        }

    }
    public void multiplicationLevelUpdates(){
        if (multiplicationLevel >= multiplicationLevelCap) {
            multiplicationLevel = 0;
            multiplicationUp++;
            notifyUser();

            multiplicationLevelCap = multiplicationLevelCap + multiplicationLevelCap;
            multiplicationRange = multiplicationRange + multiplicationRange;
        }
    }
    public void divisionLevelUpdates(){
       if (divisionLevel >= divisionLevelCap){
           divisionLevel = 0;
           divisionUp++;
           notifyUser();

           divisionLevelCap  = divisionLevelCap + divisionLevelCap;
           divisionRange = divisionRange + divisionRange;
       }
    }


    public void levelUpdates(){
        additionLevelUpdates();
        subtractionLevelUpdates();
        multiplicationLevelUpdates();
        divisionLevelUpdates();
    }
    public void leveling(int level , int levelCap){
        ProgressBar progres = new ProgressBar(this);
        progres = findViewById(R.id.progress_bar);
        progres.setProgress(level);
        progres.setMax(levelCap);


    }

    public void setDecision(View view) {
        saveLevel();
        loadLevel();
        clickCount = 1;
        switch (view.getId()) {
                case R.id.toAddition:
                    decision = "addition";
                    setTheme();
                    levelUpdates();
                    break;

                case R.id.toSubtraction:
                    decision = "subtraction";
                    levelUpdates();
                    setTheme();
                    break;

                case R.id.toMultiplication:
                    decision = "multiplication";
                    setTheme();
                    break;

                case R.id.toDivision:
                    decision = "division";
                    setTheme();
                    break;
            }

    }

    public void additionView(){
        toAddition.setBackgroundColor(darkerBlue);
        toSubtraction.setBackgroundColor(darkestPink);
        toMultiplication.setBackgroundColor(darkestPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(false);
        toSubtraction.setEnabled(true);
        toMultiplication.setEnabled(true);
        toDivision.setEnabled(true);
    }
    public void subtractionView(){
        toAddition.setBackgroundColor(darkestBlue);
        toSubtraction.setBackgroundColor(darkerPink);
        toMultiplication.setBackgroundColor(darkestPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(true);
        toSubtraction.setEnabled(false);
        toMultiplication.setEnabled(true);
        toDivision.setEnabled(true);
    }
    public void multiplicationView(){
        toAddition.setBackgroundColor(darkestBlue);
        toSubtraction.setBackgroundColor(darkestPink);
        toMultiplication.setBackgroundColor(darkerPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(true);
        toSubtraction.setEnabled(true);
        toMultiplication.setEnabled(false);
        toDivision.setEnabled(true);
    }
    public void divisionView(){
        toAddition.setBackgroundColor(darkestBlue);
        toSubtraction.setBackgroundColor(darkestPink);
        toMultiplication.setBackgroundColor(darkestPurple);
        toDivision.setBackgroundColor(darkerYellow);

        toAddition.setEnabled(true);
        toSubtraction.setEnabled(true);
        toMultiplication.setEnabled(true);
        toDivision.setEnabled(false);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveLevel();
    }

    public void randomize() {
        result.setText("X");
        displayLevels();
        generalResult = mathOperator.getResult();


        Random buttonRandomizer = new Random();
        int number = buttonRandomizer.nextInt(3) + 1;

        firstNumber.setText(String.valueOf(mathOperator.getFirstNumber()));
        secondNumber.setText(String.valueOf(mathOperator.getSecondNumber()));

        if (number == 1) {
            firstRandom.setText(String.valueOf(mathOperator.getRandomResultOne()));
            secondRandom.setText(String.valueOf(mathOperator.getResult()));
            thirdRandom.setText(String.valueOf(mathOperator.getRandomResultTwo()));
        } else if (number == 2) {
            firstRandom.setText(String.valueOf(mathOperator.getRandomResultOne()));
            thirdRandom.setText(String.valueOf(mathOperator.getResult()));
            secondRandom.setText(String.valueOf(mathOperator.getRandomResultTwo()));
        } else {
            thirdRandom.setText(String.valueOf(mathOperator.getRandomResultOne()));
            firstRandom.setText(String.valueOf(mathOperator.getResult()));
            secondRandom.setText(String.valueOf(mathOperator.getRandomResultTwo()));
        }
    }


    public void answer(View view) {
       // saveLevel();


        switch (view.getId()) {
            case R.id.first_random:
                if (Integer.valueOf((String) firstRandom.getText()) == generalResult) {
                    result.setText(String.valueOf(firstRandom.getText()));
                    //levelUp();
                    displayLevels();




                    playSpecificFile();

                    clickCount++;
                    reachedFive();

                    colorChangerCorrect(firstRandom);
                }
                else {
                    colorChangerIncorrect(firstRandom);
                    clickCount = 1;
                }
                break;

            case R.id.second_random:
                if (Integer.valueOf((String) secondRandom.getText()) == generalResult) {
                    result.setText(secondRandom.getText());

                    playSpecificFile();

                    clickCount++;
                    reachedFive();

                    displayLevels();
                    colorChangerCorrect(secondRandom);
                }
                else {
                    colorChangerIncorrect(secondRandom);
                    clickCount = 1;

                }
                break;

            case R.id.third_random:
                if (Integer.valueOf((String) thirdRandom.getText()) == generalResult) {
                    result.setText(thirdRandom.getText());
                    displayLevels();

                    playSpecificFile();

                    clickCount++;
                    reachedFive();

                    colorChangerCorrect(thirdRandom);
                }
                else {
                    colorChangerIncorrect(thirdRandom);
                    clickCount = 1;

                }
                break;
        }


    }


    public void reachedFive(){
        if (clickCount == 9) {
            clickCount = 1;
        }
    }

    public void playSpecificFile() {

        if (clickCount == 1) {
            player1.start();

        }

         if (clickCount == 2) {

             player2.start();
        }

        if (clickCount == 3) {

            player3.start();

        }

        if (clickCount == 4){

            player4.start();
        }

        if (clickCount == 5){
            player5.start();
        }

        if (clickCount == 6){
            player6.start();
        }

        if (clickCount == 7){
            player7.start();
        }

        if (clickCount == 8){
            player8.start();
        }


        }

    public String levelUpMessage(int n , String s){
        return "You reached level " + n + " in " +  s  +". \n ";
    }

    public void initializeSounds() {
        try {
            player1.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.one));
            player1.prepare();
           //g[ player1.setLooping(true);

            player2.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.two));
            player2.prepare();

            player3.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.tree));
            player3.prepare();

            player4.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.four));
            player4.prepare();

            player5.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.five));
            player5.prepare();

            player6.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.six));
            player6.prepare();

            player7.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.seven));
            player7.prepare();

            player8.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.eight));
            player8.prepare();

            player9.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.level_up));
            player9.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public void colorChangerCorrect(final Button button_id){
        button_id.setBackgroundColor(darkestLime);
        blockMultipleClicks();
        levelUpdates();
        levelUpMethod(12);
        levelProgressBarUpdate();
        saveLevel();
        //loadLevel();



        new CountDownTimer(700, 50) {

            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {

                if (decision.equals("addition")) {
                    button_id.setBackgroundColor(lightBlue);
                    mathOperator.addition(additionRange);
                    randomize();

                    levelUpMethod(13);
                    levelProgressBarUpdate();

                    displayLevels();
                }
                else if (decision.equals("subtraction")){
                    button_id.setBackgroundColor(lightPink);
                    mathOperator.subtraction(subtractionRange);
                    randomize();

                    levelUpMethod(13);
                    levelProgressBarUpdate();

                }
                else if (decision.equals("multiplication")){
                    button_id.setBackgroundColor(lightPurple);
                    mathOperator.multiplication(multiplicationRange);
                    randomize();
                    levelUpMethod(13);
                    levelProgressBarUpdate();

                }
                else if (decision.equals("division")){
                    button_id.setBackgroundColor(lightYellow);
                    mathOperator.division(divisionRange);
                    randomize();
                    levelUpMethod(13);
                    levelProgressBarUpdate();

                }

                //playSpecificFile();

            }
        }.start();
    }
    public void colorChangerIncorrect(final Button button_id){
        button_id.setBackgroundColor(darkestRed);
        blockMultipleClicks();
        new CountDownTimer(1000, 50) {

            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {
                if (decision.equals("addition")) {
                    button_id.setBackgroundColor(lightBlue);
                    clickCount = 1;
                }
                else if (decision.equals("subtraction")){
                    button_id.setBackgroundColor(lightPink);
                    clickCount = 1;
                }
                else if (decision.equals("multiplication")){
                    button_id.setBackgroundColor(lightPurple);
                    clickCount = 1;
                }
                else if (decision.equals("division")){
                    button_id.setBackgroundColor(lightYellow);
                    clickCount = 1;
                }
            }
        }.start();
    }

    public void levelProgressBarUpdate(){
        if (decision.equals("addition")) {
            additionLevelUpdates();
            leveling(additionLevel,additionLevelCap);
            displayLevels();
        }

        else if (decision.equals("subtraction")){
            subtractionLevelUpdates();
            leveling(subtractionLevel,subtractionLevelCap);
            displayLevels();
        }
        else if (decision.equals("multiplication")){
            multiplicationLevelUpdates();
            leveling(multiplicationLevel,multiplicationLevelCap);
            displayLevels();
        }
        else if (decision.equals("division")){
            divisionLevelUpdates();
            leveling(divisionLevel,divisionLevelCap);
            displayLevels();

        }
    }

    public void levelUpNotify(String n){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        player9.start();
        final View levelView = inflater.inflate(R.layout.pop_up, null);
        dialogBuilder.setView(levelView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        alertDialog.show();


        LinearLayout level = levelView.findViewById(R.id.level_holder);
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

        TextView levelUp = levelView.findViewById(R.id.level_up_text);
       // levelUp.setText(n);





        if (decision.equals("addition")){
            levelUp.setText(n);

            levelUp.setTextColor(darkestBlue);


        }

        else if (decision.equals("subtraction")){
            levelUp.setText(n);

            levelUp.setTextColor(darkestPink);


        }
        else if (decision.equals("multiplication")){
            levelUp.setText(n);

            levelUp.setTextColor(darkestPurple);


        }
        else if (decision.equals("division")){
            levelUp.setText(n);

            levelUp.setTextColor(darkestYellow);


        }



    }


    public void levelUpMethod(int n){
        if (decision.equals("addition")) {
            additionLevel = additionLevel + n;
        }
        else if (decision.equals("subtraction")){
            subtractionLevel = subtractionLevel + n;
        }
        else if (decision.equals("multiplication")){
            multiplicationLevel = multiplicationLevel + n;
        }
        else if (decision.equals("division")){
            divisionLevel = divisionLevel + n;

        }
    }


    public void blockMultipleClicks(){
        firstRandom.setEnabled(false);
        secondRandom.setEnabled(false);
        thirdRandom.setEnabled(false);

        new CountDownTimer(1000, 50) {
            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {
                firstRandom.setEnabled(true);
                secondRandom.setEnabled(true);
                thirdRandom.setEnabled(true);
            }
        }.start();
    }




    public void colorThemeBlue(){
        fatherLayout.setBackgroundColor(darkerBlue);
        equation_holder.setBackgroundColor(darkestBlue);
        number_holder.setBackgroundColor(darkestBlue);

        firstRandom.setBackgroundColor(lightBlue);
        firstRandom.setTextColor(darkestBlue);

        secondRandom.setBackgroundColor(lightBlue);
        secondRandom.setTextColor(darkestBlue);

        thirdRandom.setBackgroundColor(lightBlue);
        thirdRandom.setTextColor(darkestBlue);

    }

    public void colorThemePink(){
        equation_holder.setBackgroundColor(darkestPink);
        number_holder.setBackgroundColor(darkestPink);
        fatherLayout.setBackgroundColor(darkerPink);

        firstRandom.setBackgroundColor(lightPink);
        firstRandom.setTextColor(darkestPink);

        secondRandom.setBackgroundColor(lightPink);
        secondRandom.setTextColor(darkestPink);

        thirdRandom.setBackgroundColor(lightPink);
        thirdRandom.setTextColor(darkestPink);
    }

    public void colorThemePurple(){
        equation_holder.setBackgroundColor(darkestPurple);
        number_holder.setBackgroundColor(darkestPurple);
        fatherLayout.setBackgroundColor(darkerPurple);

        firstRandom.setBackgroundColor(lightPurple);
        firstRandom.setTextColor(darkestPurple);

        secondRandom.setBackgroundColor(lightPurple);
        secondRandom.setTextColor(darkestPurple);

        thirdRandom.setBackgroundColor(lightPurple);
        thirdRandom.setTextColor(darkestPurple);
    }

    public void colorThemeYellow(){
        equation_holder.setBackgroundColor(darkestYellow);
        number_holder.setBackgroundColor(darkestYellow);
        fatherLayout.setBackgroundColor(darkerYellow);

        firstRandom.setBackgroundColor(lightYellow);
        firstRandom.setTextColor(darkestYellow);

        secondRandom.setBackgroundColor(lightYellow);
        secondRandom.setTextColor(darkestYellow);

        thirdRandom.setBackgroundColor(lightYellow);
        thirdRandom.setTextColor(darkestYellow);
    }





}
