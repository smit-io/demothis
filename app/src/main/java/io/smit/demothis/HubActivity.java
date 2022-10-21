package io.smit.demothis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import io.smit.demothis.rest.constants.SerializedNames;
import io.smit.demothis.rest.constants.Warning;
import io.smit.demothis.rest.gsonserializer.LocalDateDeserializer;
import io.smit.demothis.rest.gsonserializer.LocalDateSerializer;
import io.smit.demothis.rest.gsonserializer.LocalDateTimeDeserializer;
import io.smit.demothis.rest.gsonserializer.LocalDateTimeSerializer;
import io.smit.demothis.rest.pojo.Customer;
import io.smit.demothis.rest.pojo.Hub;
import kotlin.collections.DoubleIterator;

public class HubActivity extends AppCompatActivity
{

    private TextView textLocalTime;
    private Button buttonFindHubs;
    private EditText editTextLatitude, editTextLongitude;
    private LocalDateTime localDateTime;
    private Customer customer;

    private double latitude;
    private double longitude;

    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        textLocalTime = (TextView) findViewById(R.id.tv_localtime);
        editTextLatitude = findViewById(R.id.etn_lat);
        editTextLongitude = findViewById(R.id.etn_long);
        buttonFindHubs = (Button) findViewById(R.id.btn_findhubs);

        customer = new Customer();


        textLocalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pick time here
                TimePickerDialog timePickerDialog = new TimePickerDialog(HubActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m)
                    {
                        hour = h;
                        minute = m;
                        Calendar calendar = Calendar.getInstance();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                        localDateTime = LocalDateTime.of(2022, 10, 1, hour, minute);
                        textLocalTime.setText(localDateTime.format(dateTimeFormatter));
                    }
                }, 12, 0, false);
                timePickerDialog.updateTime(hour, minute);
                timePickerDialog.show();
            }

        });

        buttonFindHubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if textview

                if (localDateTime == null)
                {
                    Snackbar.make(view, Warning.NO_DATETIME, Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    String lat = editTextLatitude.getText().toString();
                    String longi = editTextLongitude.getText().toString();
                    if (!lat.isEmpty() && !longi.isEmpty())
                    {
                        latitude = Double.valueOf(editTextLatitude.getText().toString());
                        longitude = Double.valueOf(editTextLongitude.getText().toString());

                        customer.setName("Parker");
                        customer.setLongitude(longitude);
                        customer.setLatitude(latitude);

                        gotoMainActivity();
                    }
                    else
                    {
                        Snackbar.make(view, Warning.NO_COORDINATES, Snackbar.LENGTH_LONG).show();
                    }
                }
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
        startActivity(intent);
    }
}