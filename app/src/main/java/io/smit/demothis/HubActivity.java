package io.smit.demothis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;

public class HubActivity extends AppCompatActivity
{

    private TextView textViewPickTime;
    private Button buttonFindHubs;
    private LocalDateTime localDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        textViewPickTime = (TextView) findViewById(R.id.tv_picktime);
        buttonFindHubs = (Button) findViewById(R.id.btn_findhubs);

        textViewPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pick time here
            }
        });

        buttonFindHubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if textview
            }
        });

        
    }
}