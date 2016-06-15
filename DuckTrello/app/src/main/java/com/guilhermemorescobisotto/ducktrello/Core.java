package com.guilhermemorescobisotto.ducktrello;

import android.app.Application;
import android.content.Intent;
import android.provider.Settings;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import io.fabric.sdk.android.Fabric;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public class Core extends Application {

    private static Core ref;
    private static String selfID;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        ref = this;
        selfID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        APIService.selfConfiguration();
        Fresco.initialize(this);

    }

    public static Core ref(){ return ref; }
    public static String selfID(){ return selfID; }

    // Create Home Screen Shortcut
    protected void addToHomeScreen(){
        //ShorcutIntent object
        Intent shortcutIntent = new Intent(getApplicationContext(),
                Core.class);

        shortcutIntent.setAction(Intent.ACTION_MAIN);
        //ShortcutIntent is added with addIntent
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Duck Trello");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                        R.mipmap.ic_launcher));

        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        //Finally broadcast the new Intent
        getApplicationContext().sendBroadcast(addIntent);
    }
}
