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

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.smit.demothis.rest.initialize.GsonInitializer;
import io.smit.demothis.rest.constants.SerializedNames;
import io.smit.demothis.rest.constants.Warning;
import io.smit.demothis.rest.pojo.Customer;

public class HubActivity extends AppCompatActivity
{
    // Declaring UI elements
    private TextView textLocalTime;
    private Button buttonFindHubs;
    private EditText editTextLatitude, editTextLongitude;
    private LocalDateTime localDateTime;
    private Customer customer;

    // Declaring input fields from the user from UI
    private double latitude;
    private double longitude;

    // To get the time for pick up
    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);


        // Intialize all UI fields
        textLocalTime = findViewById(R.id.tv_localtime);
        editTextLatitude = findViewById(R.id.etn_lat);
        editTextLongitude = findViewById(R.id.etn_long);
        buttonFindHubs = findViewById(R.id.btn_findhubs);

        // Initialize a new customer
        customer = new Customer();

        // Display a time dialog picker when clicked on the time picker textview
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
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                        localDateTime = LocalDateTime.of(2022, 10, 1, hour, minute);
                        //Toast.makeText(getBaseContext(), localDateTime.toString(), Toast.LENGTH_LONG).show();
                        textLocalTime.setText(localDateTime.format(dateTimeFormatter));
                    }
                }, 12, 0, false);
                timePickerDialog.updateTime(hour, minute);
                timePickerDialog.show();
            }

        });

        buttonFindHubs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                // Some basic warning handling to check customer date time is not empty and check if the coordinates are entered

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

                        latitude = Double.parseDouble(editTextLatitude.getText().toString());
                        longitude = Double.parseDouble(editTextLongitude.getText().toString());

                        // Hard coded name for customer, not good practice but good enough for the demo
                        customer.setName("John");
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

    private void gotoMainActivity()
    {
        Intent intent = new Intent(HubActivity.this, MainActivity.class);
        Gson gson = GsonInitializer.getGson();
        intent.putExtra(SerializedNames.CUSTOMER, gson.toJson(customer));
        intent.putExtra(SerializedNames.LOCALDATETIME, gson.toJson(localDateTime));
        startActivity(intent);
    }


}