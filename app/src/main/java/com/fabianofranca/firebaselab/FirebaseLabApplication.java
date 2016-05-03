package com.fabianofranca.firebaselab;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by fabiano on 02/05/2016.
 */
public class FirebaseLabApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
