package com.example.meetvirtually;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

public class ConferenceActivity extends AppCompatActivity {
    TextView meetingIDText;
    ImageView shareBtn;

    String meetingId,userId,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);


        meetingIDText = findViewById(R.id.meeting_id_textview);
        shareBtn = findViewById(R.id.share_btn);

        meetingId = getIntent().getStringExtra("meeting_ID");
        userId = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");


        meetingIDText.setText("Meeting ID : "+meetingId);

        addFragment();
        shareBtn.setOnClickListener((v) ->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"Join meeting in Meet Virtually App \n Meeting ID : "+meetingId);
            startActivity(Intent.createChooser(intent,"Share Via"));
        });
    }

    public void addFragment() {
        long appID = AppConstants.appId;
        String appSign = AppConstants.appSign;



        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
//        Turning Of Camera when joining By default
        config.turnOnCameraWhenJoining = false;
        //        Turning Of Microphone when joining By default
        config.turnOnMicrophoneWhenJoining = false;

        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userId, name,meetingId,config);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}