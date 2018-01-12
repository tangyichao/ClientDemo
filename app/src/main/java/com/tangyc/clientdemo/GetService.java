package com.tangyc.clientdemo;

import retrofit.client.Response;
import retrofit.http.GET;
import rx.Observable;

/**
 * @author tangyichao.
 */

public interface GetService {
    @GET("/")
    Observable<Response> listRepos();
}
