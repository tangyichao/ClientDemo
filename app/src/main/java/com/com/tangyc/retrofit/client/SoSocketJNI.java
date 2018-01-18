package com.com.tangyc.retrofit.client;

import retrofit.client.Request;
import retrofit.client.Response;

/**
 * 前三个接口参照HttpURLConnection
 * @author tangyichao.
 */

public class SoSocketJNI {
    static {
        System.loadLibrary("SoSocket");
    }
    //*******************************参照HttpURLConnection 请求方式接口********************//
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





    /************************************************第二种JNI实现方式*****************************************/


    /**
     * 只能普通请求
     * 把Request对象 封装 通过JNI 封装成Response 然后获取Response内容
     * @param request Request请求内容
     * @return  Response  返回内容
     *
     */
    public static native Response requestToResponse(Request request);
}
