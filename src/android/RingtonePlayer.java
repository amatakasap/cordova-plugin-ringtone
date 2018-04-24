package com.cordova.plugins.ringtone;

import android.content.Context;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.net.Uri;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

public class RingtonePlayer extends CordovaPlugin {
    private static final String TAG = "Ringtone";
    private Ringtone mRingtone = null;
    private Timer mTimer = null;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = this.cordova.getActivity().getApplicationContext();
        Uri uri;

        uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if (mRingtone == null) {
            mRingtone = RingtoneManager.getRingtone(context, uri);
        }

        if (action.equals("play")) {
            play();
        } else if (action.equals("stop")) {
            stop();
        }

        return true;
    }

    private void play() {
        if (mRingtone.isPlaying()) {
            return;
        }
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (!mRingtone.isPlaying()) {
                    mRingtone.play();
                }
            }
        }, 1000 * 1, 1000 * 1);
        mRingtone.play();
    }

    private void stop() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mRingtone.isPlaying()) {
            mRingtone.stop();
        }
    }
}
