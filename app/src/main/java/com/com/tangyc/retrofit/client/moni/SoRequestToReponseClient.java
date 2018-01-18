package com.com.tangyc.retrofit.client.moni;

import android.util.Log;

import com.com.tangyc.retrofit.client.SoSocketJNI;

import java.io.IOException;
import java.util.List;

import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * @author tangyichao.
 */

public class SoRequestToReponseClient implements Client {
    @Override
    public Response execute(Request request) throws IOException {
        long time=System.currentTimeMillis();
        Response response= SoSocketJNI.requestToResponse(request);
        Log.i("DEBUG",response.getReason());
        Log.i("DEBUG",response.getUrl());
        Log.i("DEBUG",response.getStatus()+"");
        List<Header> arrayList=  response.getHeaders();
        for(Header header:arrayList){
            Log.i("DEBUG","name:"+header.getName()+"value:"+header.getValue());
        }
        Log.i("DEBUG",response.getBody().mimeType());
        Log.i("DEBUG",response.getBody().length()+"");
        return response;
    }
}
