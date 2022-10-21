package io.smit.demothis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.smit.demothis.rest.constants.SerializedNames;
import io.smit.demothis.rest.gsonserializer.LocalDateDeserializer;
import io.smit.demothis.rest.gsonserializer.LocalDateSerializer;
import io.smit.demothis.rest.gsonserializer.LocalDateTimeDeserializer;
import io.smit.demothis.rest.gsonserializer.LocalDateTimeSerializer;
import io.smit.demothis.rest.pojo.Customer;
import io.smit.demothis.rest.pojo.Hub;

public class HubActivity extends AppCompatActivity
{

    private TextView textViewPickTime;
    private Button buttonFindHubs;
    private LocalDateTime localDateTime;

    private double latitude;
    private double longitude;

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

    private void gotoMainActivity() {
        Intent intent = new Intent(HubActivity.this, MainActivity.class);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();

        Customer customer = new Customer();
        customer.setLatitude(latitude);
        customer.setLongitude(longitude);
        // This can be any name, just hard coded for the demo
        customer.setName("Parker");
        intent.putExtra(SerializedNames.CUSTOMER, gson.toJson(customer));
        intent.putExtra(SerializedNames.LOCALDATETIME, gson.toJson(localDateTime));
    }
}