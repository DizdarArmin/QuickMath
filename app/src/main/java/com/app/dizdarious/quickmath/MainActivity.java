package com.app.dizdarious.quickmath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.IOException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;



public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    Intent intent;
    MediaPlayer loopPlayer;
    ToggleButton musicButton;
    int knowledegCoin;

    TextView additionCorrect;
    TextView additionIncorrect;

    TextView subtractionCorrect;
    TextView subtractionIncorrect;

    TextView multiplicationCorrect;
    TextView multiplicationIncorrect;

    TextView divisionCorrect;
    TextView divisionIncorrect;



    int soundOn;
    int soundOff;
    private AdView mAdView;
    private RewardedVideoAd mRewardedVideoAd;
    private Context myContext;

    int soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toast.makeText(this,String.valueOf(getCoin()),Toast.LENGTH_LONG).show();

        loopPlayer = new MediaPlayer();
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");// your admob app id

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
            loadRewardedVideoAd();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();
                int reward = rewardItem.getAmount();
                knowledegCoin = knowledegCoin + reward;
                editor.putInt("knowledge_coin",knowledegCoin);
                editor.commit();

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        });


        soundOff = ResourcesCompat.getColor(getResources(), R.color.darkerRed, null);
        soundOn = ResourcesCompat.getColor(getResources(), R.color.darkerLime, null);



        SharedPreferences prefs = getSharedPreferences("LEVEL", MODE_PRIVATE);


        knowledegCoin = prefs.getInt("knowledge_coin",-1);

        soundSwitch = 1;
        soundSwitch = prefs.getInt("sound",-1);
        setSoundValue(soundSwitch);

        musicButton = findViewById(R.id.sound_on_off);

        if (soundSwitch==1){
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOn);
        }
        else if(soundSwitch==0){
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOff);
        }

        loadRewardedVideoAd();


    } //END OF ONCREATE

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    public void displayAdd(View v){
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }


    public void soundOnOff(View v){
        if(musicButton.isChecked()){
            setSoundValue(0);
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOff);
        }
        else {
            setSoundValue(1);
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOn);
        }


    }




    public void setSoundValue(int n){
        SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();
        editor.putInt("sound",n);
        editor.commit();
    }

    public int getCoin(){
        SharedPreferences prefs = getSharedPreferences("LEVEL", MODE_PRIVATE);
        return prefs.getInt("knowledge_coin",-1);
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




        public void reset(View v){
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            final View levelView = inflater.inflate(R.layout.reset, null);
            dialogBuilder.setView(levelView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();

            TextView levelUp = levelView.findViewById(R.id.reset_text);
            levelUp.setText("You want to start from begining?");


            ImageView image = levelView.findViewById(R.id.level_up_image);
            image.setImageResource(R.drawable.kids);

            Button reset = levelView.findViewById(R.id.reset_button);
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save();
                    alertDialog.cancel();
                }
            });

            Button cancel = levelView.findViewById(R.id.cancel_button);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });






    }

    public void loadResult(){
        SharedPreferences prefs = getSharedPreferences("LEVEL", MODE_PRIVATE);
        additionCorrect.setText(String.valueOf(prefs.getInt("additionCorrect", -1)));
        additionIncorrect.setText(String.valueOf(prefs.getInt("additionIncorrect",-1)));

        subtractionCorrect.setText(String.valueOf(prefs.getInt("subtractionCorrect",-1)));
        subtractionIncorrect.setText(String.valueOf(prefs.getInt("subtractionIncorrect",-1)));

        multiplicationCorrect.setText(String.valueOf(prefs.getInt("multiplicationCorrect",-1)));
        multiplicationIncorrect.setText(String.valueOf(prefs.getInt("multiplicationIncorrect",-1)));

        divisionCorrect.setText(String.valueOf(prefs.getInt("divisionCorrect",-1)));
        divisionIncorrect.setText(String.valueOf(prefs.getInt("divisionIncorrect",-1)));
    }

    public void showResult(View v){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View resultView = inflater.inflate(R.layout.result_dialog, null);
        dialogBuilder.setView(resultView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        additionCorrect = resultView.findViewById(R.id.a_correct);
        additionIncorrect = resultView.findViewById(R.id.a_incorrect);

        subtractionCorrect = resultView.findViewById(R.id.s_correct);
        subtractionIncorrect = resultView.findViewById(R.id.s_incorrect);

        multiplicationCorrect = resultView.findViewById(R.id.m_correct);
        multiplicationIncorrect = resultView.findViewById(R.id.m_incorrect);

        divisionCorrect = resultView.findViewById(R.id.d_correct);
        divisionIncorrect = resultView.findViewById(R.id.d_incorrect);
        loadResult();

        alertDialog.show();



    }


    public void storeScreen(View v){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View storeView = inflater.inflate(R.layout.buy, null);
        dialogBuilder.setView(storeView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        alertDialog.show();



    }


    public void save(){
        SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();

        editor.putInt("addition", 1);
        editor.putInt("addition_exp", 0);
        editor.putInt("addition_level_cap", 100);
        editor.putInt("additionCorrect", 0);
        editor.putInt("additionIncorrect", 0);

        editor.putInt("subtraction", 1);
        editor.putInt("subtraction_exp" , 0);
        editor.putInt("subtraction_level_cap", 100);
        editor.putInt("subtractionCorrect", 0);
        editor.putInt("subtractionIncorrect", 0);


        editor.putInt("multiplication" , 1);
        editor.putInt("multiplication_exp", 0);
        editor.putInt("multiplication_level_cap", 100);
        editor.putInt("multiplicationCorrect", 0);
        editor.putInt("multiplicationIncorrect",0);

        editor.putInt("division" , 1);
        editor.putInt("division_exp", 0);
        editor.putInt("division_level_cap", 100);
        editor.putInt("divisionCorrect", 0);
        editor.putInt("divisionIncorrect", 0);

        editor.commit();
    }

}

