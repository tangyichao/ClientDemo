package com.tangyc.clientdemo;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * @author tangyichao.
 */

public interface MyService {
    @GET("/api/data/{Android}/10/1")
    Observable<GithubTest> listRepos(@Path("Android") String type);
    @GET("/51f/2c1/51f2c1f4aa40074e2488e064fbe2976d/640.jpg")
    Observable<Response> imageRepos();
}
