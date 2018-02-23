package com.app.dizdarious.quickmath;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    Button firstRandom;
    Button secondRandom;
    Button thirdRandom;

    int mathLevel = 0;
    int range = 10;
    int levelCap = 20;


    Addition addition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);







        Intent intent = getIntent();
        String decision = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

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

        firstRandom.setBackgroundColor(Color.WHITE);
        secondRandom.setBackgroundColor(Color.WHITE);
        thirdRandom.setBackgroundColor(Color.WHITE);


        if (decision.equals("addition")) {
                    operator.setText("+");
                    randomizeAddition();
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
       // firstRandom.setBackgroundColor(Color.BLACK);
       // firstRandom.setAlpha(0.7f);

       // secondRandom.setBackgroundColor(Color.BLACK);
        //secondRandom.setAlpha(0.7f);

       // thirdRandom.setBackgroundColor(Color.BLACK);
        //thirdRandom.setAlpha(0.7f);


        result.setText("X");
        addition = new Addition(range);

        Random buttonRandomizer = new Random();
        int number = buttonRandomizer.nextInt(3) + 1;

        firstNumber.setText(String.valueOf(addition.getFirstNumber()));
        secondNumber.setText(String.valueOf(addition.getSecondNumber()));
        // result.setText(String.valueOf (addition.getResult()));

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
                    lastResult.setText("Last result: " + first + " " + operator + " " +second + " " +equals + " " + lastResultLocal);
                    mathLevel++;
                    levelUpdates();
                    levelView.setText(String.valueOf(ofLevel()));
                    colorChangerCorrect(firstRandom);
                }
                else {
                    colorChangerMistake(firstRandom);
                }

                break;

            case R.id.second_random:
                if (Integer.valueOf((String) secondRandom.getText()) == addition.getResult()) {
                    result.setText(secondRandom.getText());
                    lastResult.setText("Last result: " + first + " " + operator + " " +second + " " +equals + " " + lastResultLocal);
                    mathLevel++;
                    levelUpdates();
                    levelView.setText(String.valueOf(ofLevel()));
                    colorChangerCorrect(secondRandom);
                }
                else {
                    colorChangerMistake(secondRandom);
                }
                break;

            case R.id.third_random:
                if (Integer.valueOf((String) thirdRandom.getText()) == addition.getResult()) {
                    result.setText(thirdRandom.getText());
                    lastResult.setText("Last result: " + first + " " + operator + " " +second + " " +equals + " " + lastResultLocal);
                    mathLevel++;
                    levelUpdates();
                    levelView.setText(String.valueOf(ofLevel()));
                    colorChangerCorrect(thirdRandom);
                }
                else {
                    colorChangerMistake(thirdRandom);
                }
                break;
        }


    }


    public void colorChangerMistake(final Button button_id){

        button_id.setBackgroundColor(Color.RED);
        new CountDownTimer(2000, 50) {

            @Override
            public void onTick(long arg0) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onFinish() {
                button_id.setBackgroundColor(Color.WHITE);
            }
        }.start();
    }

    public void colorChangerCorrect(final Button button_id){

        button_id.setBackgroundColor(Color.GREEN);
        new CountDownTimer(2000, 50) {

            @Override
            public void onTick(long arg0) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onFinish() {
                button_id.setBackgroundColor(Color.WHITE);
                randomizeAddition();
            }
        }.start();
    }



    void levelUp(){
        mathLevel++;
    }

    int getLevel(){
        return mathLevel;
    }

    void sleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
