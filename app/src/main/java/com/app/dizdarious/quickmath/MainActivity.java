package com.app.dizdarious.quickmath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    Intent intent;
    MediaPlayer loopPlayer;
    ToggleButton musicButton;

    int knowledgeCoin;
    int wisdomCoin;

    TextView additionCorrect;
    TextView additionIncorrect;

    TextView subtractionCorrect;
    TextView subtractionIncorrect;

    TextView multiplicationCorrect;
    TextView multiplicationIncorrect;

    TextView divisionCorrect;
    TextView divisionIncorrect;

    TextView knowledge;
    TextView wisdom;



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

        knowledge = findViewById(R.id.knowledge_coin);
        wisdom = findViewById(R.id.wisdom_coin);



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
            Toast.makeText(getApplicationContext(),"You will be rewarded with 25 Knowledge coins and 5 Wisdom coins",Toast.LENGTH_LONG).show();
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
              saveCoins(rewardItem.getAmount()+15);



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


        knowledgeCoin = prefs.getInt("knowledge_coin",0);
        wisdomCoin = prefs.getInt("wisdom_coin",0);

        knowledge.setText(String.valueOf(knowledgeCoin));
        wisdom.setText(String.valueOf(wisdomCoin));


        soundSwitch = prefs.getInt("sound",0);
       // setSoundValue(soundSwitch);

        musicButton = findViewById(R.id.sound_on_off);

        if (soundSwitch==1){
            musicButton.setChecked(true);
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOn);
            setSoundValue(1);


        }
        else {
            musicButton.setChecked(false);
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOff);
            setSoundValue(0);

        }

        loadRewardedVideoAd();
        Toast();

    } //END OF ONCREATE


    public void saveCoins(int n){
        SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();
        editor.putInt("knowledge_coin", knowledgeCoin + n);
        editor.putInt("wisdom_coin", wisdomCoin + n/5);
        editor.commit();

        SharedPreferences prefs = getSharedPreferences("LEVEL", MODE_PRIVATE);
        knowledgeCoin = prefs.getInt("knowledge_coin",0);
        wisdomCoin = prefs.getInt("wisdom_coin",0);

        knowledge.setText(String.valueOf(knowledgeCoin));
        wisdom.setText(String.valueOf(wisdomCoin));

    }

    public void buyWisdom(int price, int quantity){
        if (knowledgeCoin<price){}


        else {
            knowledgeCoin = knowledgeCoin - price;
            wisdomCoin = wisdomCoin + quantity;

            SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();
            editor.putInt("knowledge_coin", knowledgeCoin);
            editor.putInt("wisdom_coin", wisdomCoin);
            editor.commit();

            SharedPreferences prefs = getSharedPreferences("LEVEL", MODE_PRIVATE);
            knowledgeCoin = prefs.getInt("knowledge_coin",0);
            wisdomCoin = prefs.getInt("wisdom_coin",0);

            knowledge.setText(String.valueOf(knowledgeCoin));
            wisdom.setText(String.valueOf(wisdomCoin));

        }

    }


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
            setSoundValue(1);
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOn);
        }
        else {
            setSoundValue(0);
            musicButton.setText("Sound");
            musicButton.setBackgroundColor(soundOff);
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

        Button first = storeView.findViewById(R.id.first);
        Button second = storeView.findViewById(R.id.second);
        Button third = storeView.findViewById(R.id.third);
        ImageView exit = storeView.findViewById(R.id.exit);
        final TextView noCoins = storeView.findViewById(R.id.no_coins);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
                noCoins.setText("");
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyWisdom(100,5);

                if (knowledgeCoin < 100){
                    noCoins.setText("You don't have enough coins");
                }
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyWisdom(200, 15);
                if (knowledgeCoin < 200) {
                    noCoins.setText("You don't have enough coins");
                }
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyWisdom(300, 25);
                if (knowledgeCoin < 300) {
                    noCoins.setText("You don't have enough coins");
                }
            }
        });




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


public void Toast(){
    LayoutInflater inflater = getLayoutInflater();
    View layout = inflater.inflate(R.layout.toast,
            (ViewGroup) findViewById(R.id.toast_layout_root));

    ImageView image = (ImageView) layout.findViewById(R.id.image);
    //image.setImageResource(R.drawable.android);
    TextView text = (TextView) layout.findViewById(R.id.text);
    text.setText("You will be rewarded with 25 Knowledge coins and 1 Wisdom coin!");

    Toast toast = new Toast(getApplicationContext());
    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(layout);
    toast.show();
}



}

