package com.mbwc.e.myapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.mbwc.e.myapplication.Global.GD;
import com.mbwc.e.myapplication.R;

/**
 * Created by E on 1/9/2017.
 */

public class BackgroundSoundService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String filename = GD.md_filename ;
        int resID=getResources().getIdentifier(filename, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, resID);// raw/s.mp3
        mediaPlayer.setVolume(100,100);
        mediaPlayer.setOnCompletionListener(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }

}
