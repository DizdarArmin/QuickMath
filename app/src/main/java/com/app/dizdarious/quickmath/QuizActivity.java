package com.app.dizdarious.quickmath;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dizdar on 2018-02-22.
 */

public class QuizActivity extends AppCompatActivity {
    TextView firstNumber; // operator
    TextView secondNumber; // operator
    TextView result;
    TextView operator;
    TextView levelView;
    TextView lastResult;

    MediaPlayer player1;
    MediaPlayer player2;
    MediaPlayer player3;
    MediaPlayer player4;
    MediaPlayer player5;


    Button firstRandom;
    Button secondRandom;
    Button thirdRandom;

    int mathLevel = 0;
    int range = 10;
    int levelCap = 20;

    ArrayList <Boolean> fiveInRow;


    ConstraintLayout fatherLayout;



    Addition addition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        fiveInRow = new ArrayList<>(4);




        Intent intent = getIntent();
        String decision = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        fatherLayout = findViewById(R.id.father);

        levelView = findViewById(R.id.levelView);
        levelView.setText(String.valueOf(ofLevel()));



        firstNumber = findViewById(R.id.first_number);
        operator = findViewById(R.id.operator);
        secondNumber = findViewById(R.id.second_number);
        result = findViewById(R.id.result);

        firstRandom = findViewById(R.id.first_random);
        secondRandom = findViewById(R.id.second_random);
        thirdRandom = findViewById(R.id.third_random);

        lastResult = findViewById(R.id.last_result);

        firstRandom.setBackgroundResource(R.drawable.my_button);
        secondRandom.setBackgroundResource(R.drawable.my_button);
        thirdRandom.setBackgroundResource(R.drawable.my_button);


            initializeSounds();

        if (decision.equals("addition")) {
            fatherLayout.setBackgroundResource(R.drawable.blueish);
            operator.setText("+");
                    randomizeAddition();
        }
        else if (decision.equals("subtraction")){
            fatherLayout.setBackgroundResource(R.drawable.greenish);
        }
        else if (decision.equals("multiplication")){
            fatherLayout.setBackgroundResource(R.drawable.purplish);
        }
        else if (decision.equals("division")){
            fatherLayout.setBackgroundResource(R.drawable.redish);
        }

    }

    public void levelUpdates(){
        if (mathLevel == 20){
            range = 25;
            levelCap = 40;
        }

        else if (mathLevel == 40){
            range = 50;
            levelCap = 60;
        }
        else if (mathLevel == 60) {
            range = 75;
            levelCap = 80;
        }
        else if (mathLevel == 80){
            range = 100;
            levelCap = 100;
        }
        else if (mathLevel == 100) {
            range = 200;
        }
    }


    public void randomizeAddition() {
        result.setText("X");
        addition = new Addition(range);

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


    String ofLevel(){
        String toReturn = String.valueOf(mathLevel) + "/" + String.valueOf(levelCap);
        return toReturn;
    }


    public void answer(View view) {
        String first = String.valueOf(addition.getFirstNumber());
        String operator = addition.getOperator();
        String second = String.valueOf(addition.getSecondNumber());
        String equals = "=";
        String lastResultLocal = String.valueOf(addition.getResult());

        switch (view.getId()) {
            case R.id.first_random:

                if (Integer.valueOf((String) firstRandom.getText()) == addition.getResult()) {
                    result.setText(String.valueOf(firstRandom.getText()));
                    mathLevel++;
                    //reachedFive();

                    levelUpdates();
                    levelView.setText(String.valueOf(ofLevel()));
                    colorChangerCorrect(firstRandom);

                }
                else {
                    colorChangerIncorrect(firstRandom);
                }

                break;

            case R.id.second_random:
                if (Integer.valueOf((String) secondRandom.getText()) == addition.getResult()) {
                    result.setText(secondRandom.getText());
                    mathLevel++;
                    //reachedFive();
                    levelUpdates();
                    levelView.setText(String.valueOf(ofLevel()));
                    colorChangerCorrect(secondRandom);
                }
                else {
                    colorChangerIncorrect(secondRandom);
                }
                break;

            case R.id.third_random:
                if (Integer.valueOf((String) thirdRandom.getText()) == addition.getResult()) {
                    result.setText(thirdRandom.getText());
                    mathLevel++;
                    //reachedFive();
                    levelUpdates();
                    levelView.setText(String.valueOf(ofLevel()));
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


        if (fiveInRow.get(0)) {

            player1.start();
        }

         if (fiveInRow.get(0) && !fiveInRow.get(1) ){

            player2.start();
        }

          if (fiveInRow.get(1) && !fiveInRow.get(2) ){

                player3.start();
        }
           if (fiveInRow.get(2) && !fiveInRow.get(3) ) {

            player4.start();
        }
          if (fiveInRow.get(3)) {

            player5.start();
        }
    }

    public void initializeSounds()  {
            try{
            player1 = new MediaPlayer();
            player1.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note1));
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
            player2 = new MediaPlayer();
            player2.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note2));
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
            player3 = new MediaPlayer();
            player3.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note3));
            } catch (IOException e) {
                e.printStackTrace();
            }



            try {
            player4 = new MediaPlayer();
            player4.setDataSource(QuizActivity.this,
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note4));
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                player5 = new MediaPlayer();
                player5.setDataSource(QuizActivity.this,
                        Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.note5));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    public void colorChangerCorrect(final Button button_id){
        button_id.setBackgroundResource(R.drawable.correct_button);
        fiveInRow.add(true);
        //Toast t =  Toast.makeText(this,fiveInRow.toString(),Toast.LENGTH_SHORT);
        //t.show();
        new CountDownTimer(500, 50) {

            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {
                button_id.setBackgroundResource(R.drawable.my_button);

                //playSpecificFile();
                randomizeAddition();
            }
        }.start();
    }
    public void colorChangerIncorrect(final Button button_id){
        button_id.setBackgroundResource(R.drawable.incorrect_button);
        fiveInRow = new ArrayList<>(4);
        new CountDownTimer(2000, 50) {

            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {
                button_id.setBackgroundResource(R.drawable.my_button);
            }
        }.start();
    }






}
