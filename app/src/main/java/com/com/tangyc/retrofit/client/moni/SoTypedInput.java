package com.com.tangyc.retrofit.client.moni;

import java.io.IOException;
import java.io.InputStream;

import retrofit.mime.TypedInput;

/**
 * @author tangyichao.
 */

public class SoTypedInput implements TypedInput {
    private String mimeType;
    private long length;
    private InputStream inputStream;

    public SoTypedInput(String mimeType,long length,InputStream inputStream){
        this.mimeType=mimeType;
        this.length=length;
        this.inputStream=inputStream;
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
        return inputStream;
    }


}
