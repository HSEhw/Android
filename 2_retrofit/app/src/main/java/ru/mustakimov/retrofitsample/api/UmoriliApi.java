package ru.mustakimov.retrofitsample.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mustakimov.retrofitsample.PostModel;

/**
 * Created by misha on 31.10.2016.
 */
public interface UmoriliApi {

    @GET("/")
    Call<PostModel> getData(@Query("service") String type);
}
