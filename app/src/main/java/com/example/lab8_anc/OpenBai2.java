package com.example.lab8_anc;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class OpenBai2 extends AppCompatActivity {

    private SeekBar seekBar;
    private ImageView pauseButton;
    private ImageView playButton;
    private ImageView forWardButton;
    private TextView tvName;
    private TextView startTimeField;
    private TextView endTimeField;

    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finaltime = 0;
    private Handler myhandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bai2);

        seekBar = findViewById(R.id.seekBar);
        pauseButton = findViewById(R.id.pauseButton);
        playButton = findViewById(R.id.playButton);
        forWardButton = findViewById(R.id.forWardButton);
        tvName = findViewById(R.id.tvName);
        startTimeField = findViewById(R.id.startTimeField);
        endTimeField = findViewById(R.id.endTimeField);

        mediaPlayer = MediaPlayer.create(this, R.raw.abc);

        seekBar.setClickable(false);
        pauseButton.setEnabled(false);

    }

    public void play(View view) {
        mediaPlayer.start();
        finaltime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekBar.setMax((int) finaltime);
            oneTimeOnly = 1;
        }

        endTimeField.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finaltime),
                TimeUnit.MILLISECONDS.toSeconds((long) finaltime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) finaltime)))
        );

        startTimeField.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) startTime)))
        );

        seekBar.setProgress((int) startTime);
        myhandler.postDelayed(UpdateSongTime, 100);
        pauseButton.setEnabled(true);
        playButton.setEnabled(false);
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            startTimeField.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int) startTime);
            myhandler.postDelayed(this, 100);
        }
    };

    public void pause(View view) {
        mediaPlayer.pause();
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
    }


    public void forward(View view) {
        int temp = (int) startTime;
        if ((temp + forwardTime) <= finaltime) {
            startTime = startTime + forwardTime;
            mediaPlayer.seekTo((int) startTime);
        }
    }

    public void stop(View view) {
        mediaPlayer.stop();
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
        forWardButton.setEnabled(false);
    }
}
