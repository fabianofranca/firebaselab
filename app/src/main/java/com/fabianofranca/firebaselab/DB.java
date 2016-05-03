package com.fabianofranca.firebaselab;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by fabiano on 02/05/2016.
 */
public class DB {

    private final String MESSAGE = "message";

    private Firebase firebase;

    public DB() {
        firebase = new Firebase("https://fb-lab.firebaseio.com/");
    }

    public void getMessage(final MessageEventListener messageEventListener) {
        firebase.child(MESSAGE).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (messageEventListener != null) {
                    String message = snapshot.getValue() != null ? snapshot.getValue().toString() : "";
                    messageEventListener.onMessageLoaded(message);
                }
            }

            @Override public void onCancelled(FirebaseError error) { }

        });
    }

    public void setMessage(String message) {
        firebase.child(MESSAGE).setValue(message);
    }

    public interface MessageEventListener {
        void onMessageLoaded(String message);
    }
}
