package com.app.dizdarious.quickmath;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    TextView lastResult;

    MediaPlayer player1;
    MediaPlayer player2;
    MediaPlayer player3;
    MediaPlayer player4;
    MediaPlayer player5;
    MediaPlayer player6;


    Button firstRandom;
    Button secondRandom;
    Button thirdRandom;


    int additionLevel = 10;
    int subtractionLevel = 10;
    int multiplicationLevel = 10;
    int divisionLevel = 10;


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



    Addition addition;
    Subtraction subtraction;
    Multiplication multiplication;
    Division division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        equation_holder = findViewById(R.id.equation_holder);
        number_holder = findViewById(R.id.number_holder);
        fatherLayout = findViewById(R.id.father);
        kids_holder= findViewById(R.id.kids_holder);

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

        lastResult = findViewById(R.id.last_result);

        clickCount = 0;

        initializeSounds();


        try {
            setTheme();
        }
        catch (InvocationTargetException e){}
        displayLevels();

    } // End of onCreate

    public void setTheme() throws InvocationTargetException {

        if (decision.equals("addition")) {
            colorThemeBlue();
            kids_holder.setImageResource(R.drawable.kid_one);
            operator.setText("+");
            randomizeAddition();
        }
        else if (decision.equals("subtraction")){
            colorThemePink();
            kids_holder.setImageResource(R.drawable.kid_four);
            randomizeSubtraction();
            operator.setText("-");
        }
        else if (decision.equals("multiplication")){
            colorThemePurple();
            kids_holder.setImageResource(R.drawable.kid_tree);
            randomizeMultiplication();
            operator.setText("*");

        }
        else if (decision.equals("division")){
            kids_holder.setImageResource(R.drawable.kid_two);
            colorThemeYellow();
            operator.setText(" / ");
            randomizeDivision();
        }


    }

    public void displayLevels(){
        additionLevelView.setText(additionToString());
        subtractionLevelView.setText(subtractionToString());
        multiplicationLevelView.setText(multiplicationToString());
        divisionLevelView.setText(divisionToString());
    }

    public String additionToString(){
            return additionLevel + " / " + additionLevelCap;
    }
    public String subtractionToString(){
        return subtractionLevel + " / " + subtractionLevelCap;
    }
    public String multiplicationToString(){
            return multiplicationLevel + " / " + multiplicationLevelCap;
    }
    public String divisionToString(){
        return divisionLevel + " / " + divisionLevelCap;
    }

    public void additionLevelUpdates(){
        if (additionLevel == 100){
            additionRange = 20;
            additionLevelCap = 200;
        }

        else if (additionLevel == 200){
            additionRange = 30;
            additionLevelCap = 400;
        }
        else if (additionLevel == 400) {
            additionRange = 40;
            additionLevelCap = 800;
        }
        else if (additionLevel == 800){
            additionRange = 50;
            additionLevelCap = 1600;
        }
        else if (additionLevel == 1600) {
            additionRange = 80;
            additionLevelCap = 3200;
        }
        else if (additionLevel == 3200) {
            additionRange = 160;
            additionLevelCap = 6400;
        }
        else if (additionLevel == 6400) {
            additionRange = 320;
            additionLevelCap = 12800;
        }
        else if (additionLevel == 12800) {
            additionRange = 640;
            additionLevelCap = 25600;
        }
        else if (additionLevel == 25600) {
            additionRange = 1280;
            additionLevelCap = 51200;
        }
    }

    public void subtractionLevelUpdates(){
        if (subtractionLevel == 100){
            subtractionRange = 20;
            subtractionLevelCap = 200;
        }

        else if (subtractionLevel == 200){
            subtractionRange = 30;
            subtractionLevelCap = 400;
        }
        else if (subtractionLevel == 400) {
            subtractionRange = 40;
            subtractionLevelCap = 800;
        }
        else if (subtractionLevel == 800){
            subtractionRange = 50;
            subtractionLevelCap = 1600;
        }
        else if (subtractionLevel == 1600) {
            subtractionRange = 80;
            subtractionLevelCap = 3200;
        }
        else if (subtractionLevel == 3200) {
            subtractionRange = 160;
            subtractionLevelCap = 6400;
        }
        else if (subtractionLevel == 6400) {
            subtractionRange = 320;
            subtractionLevelCap = 12800;
        }
        else if (subtractionLevel == 12800) {
            subtractionRange = 640;
            subtractionLevelCap = 25600;
        }
        else if (subtractionLevel == 25600) {
            subtractionRange = 1280;
            subtractionLevelCap = 51200;
        }
    }

    public void multiplicationLevelUpdates(){
        if (multiplicationLevel == 100){
            multiplicationRange = 20;
            multiplicationLevelCap = 200;
        }

        else if (multiplicationLevel == 200){
            multiplicationRange = 30;
            multiplicationLevelCap = 400;
        }
        else if (multiplicationLevel == 400) {
            multiplicationRange = 40;
            multiplicationLevelCap = 800;
        }
        else if (multiplicationLevel == 800){
            multiplicationRange = 50;
            multiplicationLevelCap = 1600;
        }
        else if (multiplicationLevel == 1600) {
            multiplicationRange = 80;
            multiplicationLevelCap = 3200;
        }
        else if (multiplicationLevel == 3200) {
            multiplicationRange = 160;
            multiplicationLevelCap = 6400;
        }
        else if (multiplicationLevel == 6400) {
            multiplicationRange = 320;
            multiplicationLevelCap = 12800;
        }
        else if (multiplicationLevel == 12800) {
            multiplicationRange = 640;
            multiplicationLevelCap = 25600;
        }
        else if (multiplicationLevel == 25600) {
            multiplicationRange = 1280;
            multiplicationLevelCap = 51200;
        }
    }

    public void divisionLevelUpdates(){
        if (divisionLevel == 100){
            divisionRange = 20;
            divisionLevelCap = 200;
        }

        else if (divisionLevel == 200){
            divisionRange = 30;
            divisionLevelCap = 400;
        }
        else if (divisionLevel == 400) {
            divisionRange = 40;
            divisionLevelCap = 800;
        }
        else if (divisionLevel == 800){
            divisionRange = 50;
            divisionLevelCap = 1600;
        }
        else if (divisionLevel == 1600) {
            divisionRange = 80;
            divisionLevelCap = 3200;
        }
        else if (divisionLevel == 3200) {
            divisionRange = 160;
            divisionLevelCap = 6400;
        }
        else if (divisionLevel == 6400) {
            divisionRange = 320;
            divisionLevelCap = 12800;
        }
        else if (divisionLevel == 12800) {
            divisionRange = 640;
            divisionLevelCap = 25600;
        }
        else if (divisionLevel == 25600) {
            divisionRange = 1280;
            divisionLevelCap = 51200;
        }
    }

    public void setDecision(View view) {

            switch (view.getId()) {
                case R.id.toAddition:

                        decision = "addition";
                        try {
                            setTheme();
                        }
                        catch (InvocationTargetException e) {}

                    break;

                case R.id.toSubtraction:

                        decision = "subtraction";
                    try {
                        setTheme();
                    }
                    catch (InvocationTargetException e) {}

                    break;

                case R.id.toMultiplication:

                        decision = "multiplication";
                    try {
                        setTheme();
                             }
                             catch (InvocationTargetException e) {}
                    break;

                case R.id.toDivision:
                    decision = "division";
                        try {
                            setTheme();
                        }
                        catch (InvocationTargetException e) {}
                    break;
            }

    }

    public void randomizeAddition() {

        toAddition.setBackgroundColor(lightBlue);
        toSubtraction.setBackgroundColor(darkestPink);
        toMultiplication.setBackgroundColor(darkestPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(false);
        toSubtraction.setEnabled(true);
        toMultiplication.setEnabled(true);
        toDivision.setEnabled(true);

        additionLevelUpdates();


        result.setText("X");
        displayLevels();
        addition = new Addition(additionRange);
        generalResult = addition.getResult();

        Random buttonRandomizer = new Random();
        int number = buttonRandomizer.nextInt(3) + 1;

        firstNumber.setText(String.valueOf(addition.getFirstNumber()));
        secondNumber.setText(String.valueOf(addition.getSecondNumber()));

        if (number == 1) {
            firstRandom.setText(String.valueOf(addition.getRandomResultOne()));
            secondRandom.setText(String.valueOf(addition.getResult()));
            thirdRandom.setText(String.valueOf(addition.getRandomResultTwo()));
        } else if (number == 2) {
            firstRandom.setText(String.valueOf(addition.getRandomResultOne()));
            thirdRandom.setText(String.valueOf(addition.getResult()));
            secondRandom.setText(String.valueOf(addition.getRandomResultTwo()));
        } else {
            thirdRandom.setText(String.valueOf(addition.getRandomResultOne()));
            firstRandom.setText(String.valueOf(addition.getResult()));
            secondRandom.setText(String.valueOf(addition.getRandomResultTwo()));
        }
    }

    public void randomizeSubtraction() {
        toAddition.setBackgroundColor(darkestBlue);
        toSubtraction.setBackgroundColor(lightPink);
        toMultiplication.setBackgroundColor(darkestPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(true);
        toSubtraction.setEnabled(false);
        toMultiplication.setEnabled(true);
        toDivision.setEnabled(true);

        subtractionLevelUpdates();

        result.setText("X");

        displayLevels();
        subtraction = new Subtraction(subtractionRange);
        generalResult = subtraction.getResult();

        Random buttonRandomizer = new Random();
        int number = buttonRandomizer.nextInt(3) + 1;

        firstNumber.setText(String.valueOf(subtraction.getFirstNumber()));
        secondNumber.setText(String.valueOf(subtraction.getSecondNumber()));

        if (number == 1) {
            firstRandom.setText(String.valueOf(subtraction.getRandomResultOne()));
            secondRandom.setText(String.valueOf(subtraction.getResult()));
            thirdRandom.setText(String.valueOf(subtraction.getRandomResultTwo()));
        } else if (number == 2) {
            firstRandom.setText(String.valueOf(subtraction.getRandomResultOne()));
            thirdRandom.setText(String.valueOf(subtraction.getResult()));
            secondRandom.setText(String.valueOf(subtraction.getRandomResultTwo()));
        } else {
            thirdRandom.setText(String.valueOf(subtraction.getRandomResultOne()));
            firstRandom.setText(String.valueOf(subtraction.getResult()));
            secondRandom.setText(String.valueOf(subtraction.getRandomResultTwo()));
        }
    }

    public void randomizeMultiplication() {
        toAddition.setBackgroundColor(darkestBlue);
        toSubtraction.setBackgroundColor(darkestPink);
        toMultiplication.setBackgroundColor(lightPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(true);
        toSubtraction.setEnabled(true);
        toMultiplication.setEnabled(false);
        toDivision.setEnabled(true);

        multiplicationLevelUpdates();

        result.setText("X");
        displayLevels();
        multiplication = new Multiplication(multiplicationRange);
        generalResult = multiplication.getResult();

        Random buttonRandomizer = new Random();
        int number = buttonRandomizer.nextInt(3) + 1;

        firstNumber.setText(String.valueOf(multiplication.getFirstNumber()));
        secondNumber.setText(String.valueOf(multiplication.getSecondNumber()));

        if (number == 1) {
            firstRandom.setText(String.valueOf(multiplication.getRandomResultOne()));
            secondRandom.setText(String.valueOf(multiplication.getResult()));
            thirdRandom.setText(String.valueOf(multiplication.getRandomResultTwo()));
        } else if (number == 2) {
            firstRandom.setText(String.valueOf(multiplication.getRandomResultOne()));
            thirdRandom.setText(String.valueOf(multiplication.getResult()));
            secondRandom.setText(String.valueOf(multiplication.getRandomResultTwo()));
        } else {
            thirdRandom.setText(String.valueOf(multiplication.getRandomResultOne()));
            firstRandom.setText(String.valueOf(multiplication.getResult()));
            secondRandom.setText(String.valueOf(multiplication.getRandomResultTwo()));
        }

    }

    public void randomizeDivision() {
        toAddition.setBackgroundColor(lightBlue);
        toSubtraction.setBackgroundColor(darkestPink);
        toMultiplication.setBackgroundColor(darkestPurple);
        toDivision.setBackgroundColor(darkestYellow);

        toAddition.setEnabled(true);
        toSubtraction.setEnabled(true);
        toMultiplication.setEnabled(true);
        toDivision.setEnabled(false);

        result.setText("X");
        displayLevels();
        division = new Division(divisionRange);
        generalResult = division.getResult();

        Random buttonRandomizer = new Random();
        int number = buttonRandomizer.nextInt(3) + 1;

        firstNumber.setText(String.valueOf(division.getFirstNumber()));
        secondNumber.setText(String.valueOf(division.getSecondNumber()));

        if (number == 1) {
            firstRandom.setText(String.valueOf(division.getRandomResultOne()));
            secondRandom.setText(String.valueOf(division.getResult()));
            thirdRandom.setText(String.valueOf(division.getRandomResultTwo()));
        } else if (number == 2) {
            firstRandom.setText(String.valueOf(division.getRandomResultOne()));
            thirdRandom.setText(String.valueOf(division.getResult()));
            secondRandom.setText(String.valueOf(division.getRandomResultTwo()));
        } else {
            thirdRandom.setText(String.valueOf(division.getRandomResultOne()));
            firstRandom.setText(String.valueOf(division.getResult()));
            secondRandom.setText(String.valueOf(division.getRandomResultTwo()));
        }
    }

    public void answer(View view) {
        switch (view.getId()) {
            case R.id.first_random:
                if (Integer.valueOf((String) firstRandom.getText()) == generalResult) {
                    result.setText(String.valueOf(firstRandom.getText()));
                    //levelUp();
                    displayLevels();

                    playSpecificFile();
                    colorChangerCorrect(firstRandom);
                }
                else {
                    colorChangerIncorrect(firstRandom);
                }
                break;

            case R.id.second_random:
                if (Integer.valueOf((String) secondRandom.getText()) == generalResult) {
                    result.setText(secondRandom.getText());

                    //levelUp();

                    playSpecificFile();
                    displayLevels();
                    colorChangerCorrect(secondRandom);
                }
                else {
                    colorChangerIncorrect(secondRandom);

                }
                break;

            case R.id.third_random:
                if (Integer.valueOf((String) thirdRandom.getText()) == generalResult) {
                    result.setText(thirdRandom.getText());
                    //levelUp();

                    playSpecificFile();
                    displayLevels();
                    colorChangerCorrect(thirdRandom);
                }
                else {
                    colorChangerIncorrect(thirdRandom);

                }
                break;
        }


    }


    public void reachedFive(){
        if (fiveInRow.get(4).equals(true)){
            this.fiveInRow = new ArrayList<>(4);
        }

    }

    public void playSpecificFile() {

        if (clickCount == 0) {
            try {
                player1.prepare();
                player1.start();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

         if (clickCount == 1) {
             player2.start();
        }

        if (clickCount == 2) {
            player3.start();
        }

        if (clickCount == 3){
            player4.start();
        }

        if (clickCount == 4){
            player5.start();
        }
        }



    public void initializeSounds() {
        try {
            player1 = new MediaPlayer();
            player1.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note1));

            player2 = new MediaPlayer();
            player2.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note2));

            player3 = new MediaPlayer();
            player3.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note3));

            player4 = new MediaPlayer();
            player4.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note4));

            player5 = new MediaPlayer();
            player5.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note5));

            player6 = new MediaPlayer();
            player6.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.level_bonus));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void colorChangerCorrect(final Button button_id){
        button_id.setBackgroundColor(darkerLime);
        blockMultipleClicks();

        //fiveInRow.add(true);
        new CountDownTimer(1000, 50) {

            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {

                if (decision.equals("addition")) {
                    button_id.setBackgroundColor(lightBlue);
                    additionLevel = additionLevel + 5;
                    additionLevelUpdates();
                    randomizeAddition();
                    clickCount++;
                }
                else if (decision.equals("subtraction")){
                    button_id.setBackgroundColor(lightPink);
                    subtractionLevel = subtractionLevel + 5;
                    subtractionLevelUpdates();
                    randomizeSubtraction();
                    clickCount++;
                }
                else if (decision.equals("multiplication")){
                    button_id.setBackgroundColor(lightPurple);
                    multiplicationLevel = multiplicationLevel + 5;
                    multiplicationLevelUpdates();
                    randomizeMultiplication();
                    clickCount++;
                }
                else if (decision.equals("division")){
                    button_id.setBackgroundColor(lightYellow);
                    divisionLevel = divisionLevel + 5;
                    divisionLevelUpdates();
                    randomizeDivision();
                    clickCount++;
                }

                //playSpecificFile();

            }
        }.start();
    }
    public void colorChangerIncorrect(final Button button_id){
        button_id.setBackgroundColor(lightRed);
        blockMultipleClicks();

        fiveInRow = new ArrayList<>(4);
        new CountDownTimer(1000, 50) {

            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {

                if (decision.equals("addition")) {
                    button_id.setBackgroundColor(lightBlue);
                    clickCount = 0;
                }
                else if (decision.equals("subtraction")){
                    button_id.setBackgroundColor(lightPink);
                    clickCount = 0;
                }
                else if (decision.equals("multiplication")){
                    button_id.setBackgroundColor(lightPurple);
                    clickCount = 0;
                }
                else if (decision.equals("division")){
                    button_id.setBackgroundColor(lightYellow);
                    clickCount = 0;
                }
            }
        }.start();
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
