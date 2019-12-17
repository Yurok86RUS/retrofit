package com.yur0k.a20191217retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccuWeather {
    @GET("currentconditions/v1")
    Call<WeatherModel> getWeather(@Query("/") Integer q, @Query("apikey=") String key);

    //"http://dataservice.accuweather.com/currentconditions/v1/294021?apikey=dNCJ5Bk0LjZE75BNfUFBgAdOFj6I7bjI"
}
