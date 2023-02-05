package com.example.fitnessfreakv1;

import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoLoop {

    public static void OnPrep(MediaController mp, VideoView veg_image) {
        veg_image.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                mp.setLooping(true);
            }
        });
    }
}
