package com.pyarinc.videodemo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class VideoPlayer extends Activity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,View.OnTouchListener {
            
    private VideoView mVV;
          private ImageView ivImage;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
 
        setContentView(R.layout.activity_main);
        ivImage = findViewById(R.id.bg_iv);
        int fileRes=0;    
        Bundle e = getIntent().getExtras();
        if (e!=null) {
            fileRes = e.getInt("fileRes");
        }
        fileRes = R.raw.payrgifcompressed;
 
        mVV = (VideoView)findViewById(R.id.myvideoview);
        mVV.setOnCompletionListener(this);
        mVV.setOnPreparedListener(this);
        mVV.setOnTouchListener(this);
 
        if (!playFileRes(fileRes)) return;
 


        mVV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                ivImage.setVisibility(View.GONE);
                mVV.start();
            }
        });
    }
          
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int fileRes = 0;
        Bundle e = getIntent().getExtras();
        if (e != null) {
            fileRes = e.getInt("fileRes");
        }
        playFileRes(fileRes);
    }
             
    private boolean playFileRes(int fileRes) {
        if (fileRes==0) {
            stopPlaying();
            return false;
        } else {
            mVV.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + fileRes));
            return true;
        }
    }
 
    public void stopPlaying() {
        mVV.stopPlayback();
        this.finish();             
    }
 
    @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }
             
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        stopPlaying();
        return true;
    }
 
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(true);
    }
}