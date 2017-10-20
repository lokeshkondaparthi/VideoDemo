package com.pyarinc.videodemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private ImageView ivImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView  = (VideoView) findViewById(R.id.myvideoview);
        ivImageView =(ImageView)findViewById(R.id.bg_iv);
        mVideoView.setVideoURI((Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.payrgifcompressed)));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mediaPlayer) {
                mVideoView.start();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        while (mediaPlayer.isPlaying()) {
                            Log.d("MainActivity", "position "+mediaPlayer.getCurrentPosition());
                            if (mediaPlayer.getCurrentPosition() == 0) {
                                ivImageView.setVisibility(View.VISIBLE);
                            }else {
                                ivImageView.setVisibility(View.GONE);
                                break;
                            }
                        }

                    }
                });

            }
        });
    }
}
