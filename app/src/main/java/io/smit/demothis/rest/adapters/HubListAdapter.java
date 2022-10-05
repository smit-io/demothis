package io.smit.demothis.rest.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.List;

import io.smit.demothis.R;
import io.smit.demothis.rest.pojo.Hub;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HubListAdapter extends ArrayAdapter<Hub>
{
    private List<Hub> hubs;
    private int resource;
    Context context;

    public HubListAdapter(@NonNull Context context, int resource, @NonNull List<Hub> hubs) {
        super(context, resource, hubs);
        this.hubs = hubs;
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String hubName = getItem(position).getName();
        LocalDateTime openingTime = getItem(position).getOpeningTime();
        LocalDateTime closingtime = getItem(position).getClosingTime();
        double lat = getItem(position).getLatitude();
        double lang = getItem(position).getLongitude();

        Hub hub = new Hub(0, hubName, openingTime, closingtime, lat, lang);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);

        TextView tvHubName = (TextView) convertView.findViewById(R.id.tv_hubname);
        TextView tvHoursOfOperation = (TextView) convertView.findViewById(R.id.tv_hoursofoperation);
        TextView tvCoordinates = (TextView) convertView.findViewById(R.id.tv_latlang);

        tvHubName.setText(hubName);
        tvHoursOfOperation.setText(openingTime.getHour() + ":" + openingTime.getMinute() + " - " + closingtime.getHour() + ":" + closingtime.getMinute());
        tvCoordinates.setText(lat + "," + lang);

        return convertView;

    }
}
