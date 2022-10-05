package io.smit.demothis.rest.retrofitapi;

import java.util.List;

import io.smit.demothis.rest.constants.Constants;
import io.smit.demothis.rest.pojo.Hub;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HubsApi {

    @GET(Constants.HUB_ENDPOINT)
    Call<List<Hub>> getAllHubs();
}
