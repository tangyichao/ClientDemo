package com.com.tangyc.retrofit.client;

import retrofit.mime.TypedOutput;

/**
 * @author tangyichao.
 */

public class SoSocketJNI {
    /**
     * 传递URL 获取唯一网络请求
     * @param url 请求链接
     * @return  唯一网络请求
     */
    public static native long initJNI(String url);

    /**
     * 发起请求的配置
     * @param handle 唯一请求标识
     * @param type  请求的类型
     * @param value  请求的数值
     */
    public static native void initType(long handle,String type,Object value);

    /**
     * 响应的配置
     * @param handle 唯一请求的标识
     * @param type 响应的类型
     * @return  响应的数值
     */
    public static native Object getType(long handle,String type);
}
