package com.tangyc.clientdemo;

import java.util.Map;

import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * @author tangyichao.
 */

public interface FormService {
    @FormUrlEncoded
    @POST("/joke/content/list.from")
    Observable<Response> listRepos(@Field("sort") String sort, @Field("page") String page, @Field("pagesize") String pagesize,@Field("time") String time, @Field("key") String key);

    @FormUrlEncoded
    @POST("/joke/content/list.from")
    Observable<Response> listRepos(@FieldMap Map<String,String> map);
}
