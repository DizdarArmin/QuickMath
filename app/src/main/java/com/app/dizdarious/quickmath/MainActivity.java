package com.app.dizdarious.quickmath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import android.widget.ToggleButton;

import java.io.IOException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;



public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    Intent intent;
    MediaPlayer loopPlayer;
    ToggleButton musicButton;
    AudioManager amanager;

    int soundOn;
    int soundOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loopPlayer = new MediaPlayer();
        initLoopPlayer();

        soundOff = ResourcesCompat.getColor(getResources(), R.color.darkerRed, null);
        soundOn = ResourcesCompat.getColor(getResources(), R.color.darkestLime, null);


        musicButton = findViewById(R.id.sound_on_off);

        musicIsOn();


    }

    public void soundOnOff(View v){
        if(musicButton.isChecked()){
            musicIsOff();
        }
        else {
            musicIsOn();
        }


    }


    public void musicIsOn(){
        musicButton.setText("Sound");
        amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        amanager.setStreamMute(AudioManager.STREAM_RING, false);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
        musicButton.setBackgroundColor(soundOn);
    }

    public void musicIsOff(){
        musicButton.setText("Sound");
        amanager =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        musicButton.setBackgroundColor(soundOff);
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loopPlayer.stop();
        loopPlayer.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loopPlayer.stop();
        loopPlayer.release();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (this.isFinishing()) {
            loopPlayer.stop();
            loopPlayer.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loopPlayer.start();
    }

    public void initLoopPlayer(){

        try {
            loopPlayer.setDataSource(getApplicationContext(),
                    Uri.parse("android.resource://com.app.dizdarious.quickmath/" + R.raw.loop));
            loopPlayer.prepare();
            loopPlayer.setLooping(true);
            loopPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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



    public void save(){
        SharedPreferences.Editor editor = getSharedPreferences("LEVEL", MODE_PRIVATE).edit();

        editor.putInt("addition", 1);
        editor.putInt("addition_exp", 0);
        editor.putInt("addition_level_cap", 100);

        editor.putInt("subtraction", 1);
        editor.putInt("subtraction_exp" , 0);
        editor.putInt("subtraction_level_cap", 100);

        editor.putInt("multiplication" , 1);
        editor.putInt("multiplication_exp", 0);
        editor.putInt("multiplication_level_cap", 100);

        editor.putInt("division" , 1);
        editor.putInt("division_exp", 0);
        editor.putInt("division_level_cap", 100);
        editor.commit();
    }

}

