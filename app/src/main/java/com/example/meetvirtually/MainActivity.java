package com.example.meetvirtually;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    TextInputEditText meetingIdInput,nameInput;
    MaterialButton joinBtn,createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        meetingIdInput = findViewById(R.id.meeting_id_input);
        nameInput = findViewById(R.id.name_input);
        joinBtn = findViewById(R.id.join_btn);
        createBtn = findViewById(R.id.create_btn);

        joinBtn.setOnClickListener((v) -> {
            String meetingId = meetingIdInput.getText().toString();
            if(meetingId.length() != 10){
                meetingIdInput.setError("Invalid Meeting Id");
                meetingIdInput.requestFocus();
                return;
            }

            String name =  nameInput.getText().toString();
            if(name.isEmpty()){
                nameInput.setError("Name is required to join the meeting");
                nameInput.requestFocus();
                return;
            }
            startMeeting(meetingId,name);

        });
        createBtn.setOnClickListener((v) -> {


            String name =  nameInput.getText().toString();
            if(name.isEmpty()){
                nameInput.setError("Name is required to create the meeting");
                nameInput.requestFocus();
                return;
            }
            startMeeting(getRandomMeetingId(),name);
        });

    }

    void startMeeting(String meetingId,String name){
        String userId = UUID.randomUUID().toString();
        Intent intent = new Intent(MainActivity.this,ConferenceActivity.class);
        intent.putExtra("meeting_ID",meetingId);
        intent.putExtra("name",name);
        intent.putExtra("user_id",userId);
        startActivity(intent);

    }
    String getRandomMeetingId(){
        StringBuilder id  = new StringBuilder();
        while (id.length()!=10){
            int random = new Random().nextInt(10);
            id.append(random);
        }
        return id.toString();
    }
}