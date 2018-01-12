package com.tangyc.clientdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * @author tangyichao
 */

public class Socket implements Client {
    private static final int CHUNK_SIZE = 4096;
    private static final int NET_ERROR_CODE = 400;

    @Override
    public Response execute(Request request) throws IOException {
        URL url = new URL(request.getUrl());
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(request.getMethod());
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setDoInput(true);
        List<Header> list = request.getHeaders();
        for (Header header : list) {
            httpURLConnection.addRequestProperty(header.getName(), header.getValue());
        }
        TypedOutput typedOutput = request.getBody();
        if (typedOutput != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", typedOutput.mimeType());
            long length = typedOutput.length();
            if (length != -1) {
                httpURLConnection.setFixedLengthStreamingMode((int) length);
                httpURLConnection.addRequestProperty("Content-Length", String.valueOf(length));
            } else {
                httpURLConnection.setChunkedStreamingMode(CHUNK_SIZE);
            }
            typedOutput.writeTo(httpURLConnection.getOutputStream());
        }
        List<Header> headers = new ArrayList<>();
        for (Map.Entry<String, List<String>> field : httpURLConnection.getHeaderFields().entrySet()) {
            String name = field.getKey();
            for (String value : field.getValue()) {
                headers.add(new Header(name, value));
            }
        }
        int status = httpURLConnection.getResponseCode();
        String reason = httpURLConnection.getResponseMessage();
        if(reason==null){
            reason="";
        }
        String mimeType = httpURLConnection.getContentType();
        int length = httpURLConnection.getContentLength();
        InputStream stream;
        if (status >= NET_ERROR_CODE) {
            stream = httpURLConnection.getErrorStream();
        } else {
            stream = httpURLConnection.getInputStream();
        }
        TypedInput responseBody = new TypedInputStream(mimeType, length, stream);
        return new Response(httpURLConnection.getURL().toString(), status, reason, headers, responseBody);
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

        @Override
        public String mimeType() {
            return mimeType;
        }

        @Override
        public long length() {
            return length;
        }

        @Override
        public InputStream in() throws IOException {
            return stream;
        }
    }

}
