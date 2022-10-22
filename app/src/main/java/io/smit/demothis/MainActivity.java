package io.smit.demothis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import io.smit.demothis.rest.initialize.GsonInitializer;
import io.smit.demothis.rest.adapters.HubListAdapter;
import io.smit.demothis.rest.constants.SerializedNames;
import io.smit.demothis.rest.initialize.RetrofitInitializer;
import io.smit.demothis.rest.pojo.Customer;
import io.smit.demothis.rest.pojo.Hub;
import io.smit.demothis.rest.retrofitapi.HubsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{

    // Define variables
    private static Gson gson;
    private static Retrofit retrofit;
    private static HubsApi hubsApi;
    private static Customer customer;
    private static List<Hub> hubList;
    private static LocalDateTime customerDateTime;
    private ListView listViewHubs;

    private static final String TAG = "Main activity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewHubs = (ListView) findViewById(R.id.lv_hubs);


        // REST API initialize
        gson = GsonInitializer.getGson();
        retrofit = RetrofitInitializer.getRetrofit();
        buildHubsApi();

        // Get data from hubactivity
        customer = gson.fromJson(getIntent().getStringExtra(SerializedNames.CUSTOMER), Customer.class);
        customerDateTime = gson.fromJson(getIntent().getStringExtra(SerializedNames.LOCALDATETIME), LocalDateTime.class);

       //Toast.makeText(MainActivity.this, getIntent().getStringExtra(SerializedNames.LOCALDATETIME), Toast.LENGTH_LONG).show();



        Toast.makeText(MainActivity.this, "Fetching hubs...", Toast.LENGTH_LONG).show();

        Call<List<Hub>> call = hubsApi.getAllHubs();

        call.enqueue(new Callback<List<Hub>>()
        {
            @Override
            public void onResponse(Call<List<Hub>> call, Response<List<Hub>> response) {
                if (!response.isSuccessful())
                {
                    Log.d(TAG, "not successful: " + response.code());
                    return;
                }
                hubList = response.body();
                hubList = customer.calculateManhattanDistance(hubList);
                Log.d("RESPONSE", "listofhubs before filter" + hubList.toString());
                filterHubsByAvailability();
                hubList.sort(Comparator.comparing(Hub::getManhattanDistance));
                Log.d("RESPONSE", "listofhubs " + hubList.toString());
                Log.d("RESPONSE", response.body().toString() + "code " + response.code());



                HubListAdapter hubListAdapter = new HubListAdapter(MainActivity.this, R.layout.row_item, hubList);
                listViewHubs.setAdapter(hubListAdapter);
                hubListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Hub>> call, Throwable t)
            {
                Log.d("FAILURE", "call " + call.request());
                Log.d("FAILURE", "failure " + t.getMessage());
            }
        });

        listViewHubs.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hub hub = (Hub) adapterView.getItemAtPosition(i);
                Snackbar.make(view, "Manhattan distance: " + hub.getManhattanDistance(), Snackbar.LENGTH_LONG).show();
            }
        });


    }

    private static void buildHubsApi()
    {
        hubsApi = retrofit.create(HubsApi.class);
    }

    public void filterHubsByAvailability()
    {
        for (Iterator<Hub> iterator = hubList.iterator(); iterator.hasNext();)
        {
            Hub hub = iterator.next();

            if(!(isCustomerTimeAfterOpening(hub.getOpeningTime(), customerDateTime) && isCustomerTimeBeforeClosing(hub.getClosingTime(), customerDateTime)))
            {
                iterator.remove();
                Toast.makeText(MainActivity.this, "Hub " + hub.getName() + " is not available", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Hub " + hub.getName() + " is available", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static boolean isCustomerTimeAfterOpening(LocalDateTime hubOpeningTime, LocalDateTime customerTime)
    {
        return customerTime.isAfter(hubOpeningTime) || customerTime.isEqual(hubOpeningTime);
    }

    private static boolean isCustomerTimeBeforeClosing(LocalDateTime hubClosingTime, LocalDateTime customerTime)
    {
        return customerTime.isBefore(hubClosingTime) || customerTime.isEqual(hubClosingTime);
    }


}