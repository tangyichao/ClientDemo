package com.tangyc.clientdemo;

import retrofit.client.Response;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author tangyichao.
 */

public interface PostService {
    /**
     * 请求地址：http://japi.juhe.cn/joke/content/list.from
     请求参数：sort=&page=&pagesize=&time=&key=1da3c9447e3b07beb0b78b19f5b5f229
     请求方式：POST
     */
    @POST("/joke/content/list.from")
    Observable<Response> listRepos(@Query("sort") String sort, @Query("page") String page, @Query("pagesize") String pagesize, @Query("time") String time, @Query("key") String key);
}
