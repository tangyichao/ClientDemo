package com.tangyc.clientdemo;

import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import rx.Observable;

/**
 * @author tangyichao.
 */

public interface MultipartService {
    @Multipart
    @POST("/joke/content/list.from")
    Observable<Response> listRepos(@Part("file")TypedFile file);

}
