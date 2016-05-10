package com.fabianofranca.firebaselab;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestFragment extends Fragment {

    private DB db;
    private TextView textMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DB();

        textMessage = (TextView)view.findViewById(R.id.text_message);
        final EditText editMessage = (EditText)view.findViewById(R.id.edit_message);

        getMessage();

        Button buttonSend = (Button)view.findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString();
                if (message != null && !message.toString().isEmpty()) {
                    db.setMessage(message);
                    getMessage();
                } else {
                    Toast.makeText(getActivity(), "Invalid message", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void getMessage() {
        final ProgressDialog progress = ProgressDialog.show(getActivity(), "",
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
