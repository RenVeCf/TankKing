package com.ipd.tankking.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ipd.tankking.R;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/18.
 */
public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    //必须要实现此方法，IBinder对象用于交换数据，此处暂时不实现
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.nh);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        play();
        return super.onStartCommand(intent, flags, startId);
    }

    //封装播放
    private void play() {
        mediaPlayer.start();
    }

    //service被关闭之前调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}