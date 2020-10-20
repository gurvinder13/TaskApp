package com.example.taskapp.network;



import com.example.model.NewsRespoonse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(ApiEndPoints.NEWS)
    Observable<NewsRespoonse> getNewsData(@Query("query")String query);
}
