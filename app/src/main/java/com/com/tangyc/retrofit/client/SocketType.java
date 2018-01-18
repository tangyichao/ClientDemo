package com.com.tangyc.retrofit.client;

/**
 * @author tangyichao.
 */

public class SocketType {
    //链接超时
    public static final String CONNECT_TIMEOUT ="ConnectTimeout";
    //读取超时
    public static final  String READ_TIMEOUT ="ReadTimeout";
    //请求方式
    public static final String REQUEST_METHOD ="RequestMethod";
    
    //设置是否从httpUrlConnection读入，默认情况下是true; 
    public static final  String DO_INPUT ="DoInput";
    // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
    // http正文内，因此需要设为true, 默认情况下是false;

    public static final  String DO_OUTPUT ="DoOutput";

    // 设定传送的内容类型是可序列化的java对象
    // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)

    public  static  final  String CONTENT_TYPE ="Content-Type";
    //设置流的固定长度
    public  static final  String FIXED_LENGTH_STREAMING_MODE ="FixedLengthStreamingMode";

    //Content-Length如果存在并且有效的话，则必须和消息内容的传输长度完全一致
    public static final  String CONTENT_LENGTH ="Content-Length";
    
    public static  final  String CHUNKED_STREAMING_MODE ="ChunkedStreamingMode";
    // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
// 所以在开发中不调用上述的connect()也可以)。
    public static  final String OUTPUT_STREAM ="OutputStream";
    //响应码  
    public  static  final  String RESPONSE_CODE ="ResponseCode";
    //获取响应码描述
    public static  final String RESPONSE_MESSAGE="ResponseMessage";

    // 返回的数据类型
    public  static  final  String RESPONSE_CONTENT_TYPE ="ContentType";
    //返回的内容长度
    public  static  final  String RESPONSE_CONTENT_LENGT="ContentLength";

}
