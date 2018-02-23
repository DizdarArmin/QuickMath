package com.app.dizdarious.quickmath;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }


    public void otherActivity(View view) {

        intent = new Intent(this, QuizActivity.class);
        switch (view.getId()) {

            case R.id.addition:
                intent.putExtra(EXTRA_MESSAGE, "addition");
                startActivity(intent);
                break;

            case R.id.subtraction:
                intent.putExtra(EXTRA_MESSAGE, "subtraction");
                startActivity(intent);
                break;

            case R.id.multiplication:
                intent.putExtra(EXTRA_MESSAGE, "multiplication");
                startActivity(intent);
                break;

            case R.id.division:
                intent.putExtra(EXTRA_MESSAGE, "division");
                startActivity(intent);
                break;



        }

    }

}
