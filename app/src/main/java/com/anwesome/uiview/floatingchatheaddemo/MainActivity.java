package com.anwesome.uiview.floatingchatheaddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anwesome.uiview.floatingchathead.FloatingChatHeadService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.start_service);
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FloatingChatHeadService.class);
                startService(intent);
                Toast.makeText(MainActivity.this,"Service is started",Toast.LENGTH_LONG).show();
            }
        });
    }
}
