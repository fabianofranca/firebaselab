package com.fabianofranca.firebaselab;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DB db;
    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB();

        textMessage = (TextView)findViewById(R.id.text_message);
        final EditText editMessage = (EditText)findViewById(R.id.edit_message);

        getMessage();

        Button buttonSend = (Button)findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString();
                if (message != null && !message.toString().isEmpty()) {
                    db.setMessage(message);
                    getMessage();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid message", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getMessage() {
        final ProgressDialog progress = ProgressDialog.show(this, "",
                "Loading. Please wait...", true);

        db.getMessage(new DB.MessageEventListener() {
            @Override
            public void onMessageLoaded(String message) {
                textMessage.setText(!message.isEmpty()? message : getString(R.string.no_message));
                progress.hide();
            }
        });
    }
}
