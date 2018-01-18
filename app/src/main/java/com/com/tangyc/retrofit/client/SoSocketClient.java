package com.com.tangyc.retrofit.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * 基于HttpUrlConnection实现
 * @author tangyichao.
 */

public class SoSocketClient implements Client {
    private static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000;
    private  static final int READ_TIMEOUT_MILLIS = 20 * 1000;
    private static final int CHUNK_SIZE = 4096;
    private static final int ERROR_NET_CODE400=400;

    public SoSocketClient() {
    }

    @Override public Response execute(Request request) throws IOException {

        long handle=SoSocketJNI.initJNI(request.getUrl());
        SoSocketJNI.initType(handle,SocketType.CONNECT_TIMEOUT,CONNECT_TIMEOUT_MILLIS);
        SoSocketJNI.initType(handle,SocketType.READ_TIMEOUT,READ_TIMEOUT_MILLIS);
        SoSocketJNI.initType(handle,SocketType.REQUEST_METHOD,request.getMethod());
        SoSocketJNI.initType(handle,SocketType.DO_INPUT,true);
        for (Header header : request.getHeaders()) {
            SoSocketJNI.initType(handle,"header",header.getName()+","+header.getValue());
        }
        TypedOutput body = request.getBody();
        if (body != null) {
            SoSocketJNI.initType(handle,SocketType.DO_INPUT,true);
            SoSocketJNI.initType(handle,SocketType.CONTENT_TYPE,body.mimeType());
            long length = body.length();
            if (length != -1) {
                SoSocketJNI.initType(handle,SocketType.FIXED_LENGTH_STREAMING_MODE,(int) length);
                SoSocketJNI.initType(handle,SocketType.CONTENT_LENGTH,String.valueOf(length));
            } else {
                SoSocketJNI.initType(handle,SocketType.CHUNKED_STREAMING_MODE,CHUNK_SIZE);
            }
            byte[] mByte= (byte[]) SoSocketJNI.getType(handle,SocketType.OUTPUT_STREAM);
            OutputStream ops=new ByteArrayOutputStream(mByte.length);
            ops.write(mByte);
            body.writeTo(ops);
            ops.flush();
            ops.close();
        }
        int status= (Integer) SoSocketJNI.getType(handle,SocketType.RESPONSE_CODE);
        String reason= (String) SoSocketJNI.getType(handle,SocketType.RESPONSE_MESSAGE);
        if (reason == null)
        {
            reason = "";
        }

       ArrayList<Header> headers = new ArrayList<Header>();
        String[] headersFields= (String[]) SoSocketJNI.getType(handle,"getHeaderFields");
        for(String header:headersFields){
            String[] h2v=header.split(",");
            String name=h2v[0];
            String value=h2v[1];
            headers.add(new Header(name,value));
        }
        String mimeType= (String) SoSocketJNI.getType(handle,SocketType.RESPONSE_CONTENT_TYPE );
        int length= (Integer) SoSocketJNI.getType(handle,SocketType.RESPONSE_CONTENT_LENGT);
        InputStream stream;
        if (status >= ERROR_NET_CODE400) {
            byte[] mByte=(byte[])SoSocketJNI.getType(handle,"ErrorStream");
            stream = new ByteArrayInputStream(mByte);
        } else {
            byte[] mByte=(byte[])SoSocketJNI.getType(handle,"InputStream");
            stream = new ByteArrayInputStream(mByte);
        }
        TypedInput responseBody = new SoSocketClient.TypedInputStream(mimeType, length, stream);
        String url= (String) SoSocketJNI.getType(handle,"URL");
        return new Response(url, status, reason, headers, responseBody);
    }

    private static class TypedInputStream implements TypedInput {
        private final String mimeType;
        private final long length;
        private final InputStream stream;

        private TypedInputStream(String mimeType, long length, InputStream stream) {
            this.mimeType = mimeType;
            this.length = length;
            this.stream = stream;
        }

        @Override public String mimeType() {
            return mimeType;
        }

        @Override public long length() {
            return length;
        }

        @Override public InputStream in() throws IOException {
            return stream;
        }
    }
}
