#include <jni.h>
#include <string>
#include <android/log.h>
#define LOG_TAG "JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jlong

JNICALL
Java_com_com_tangyc_retrofit_client_SoSocketJNI_initJNI(
        JNIEnv *env,
        jobject /* this */,jstring) {
    return 0;
}
extern "C"
JNIEXPORT jobject

JNICALL
Java_com_com_tangyc_retrofit_client_SoSocketJNI_initType(
        JNIEnv *env,
        jobject /* this */,jlong,jstring,jobject) {
    return 0;
}
extern "C"
JNIEXPORT jobject

JNICALL
Java_com_com_tangyc_retrofit_client_SoSocketJNI_getType(
        JNIEnv *env,
        jobject /* this */,jlong,jobject) {
    return 0;
}

extern "C"
JNIEXPORT jobject

JNICALL

Java_com_com_tangyc_retrofit_client_SoSocketJNI_requestToResponse(
        JNIEnv *env,
        jobject /* this */,jobject obj_request) {
    jclass request_cls = env->GetObjectClass(obj_request);

    jfieldID methodID = env->GetFieldID(request_cls,"method","Ljava/lang/String;");
    jstring method = (jstring)env->GetObjectField(obj_request , methodID);//获得属性值
    const char * c_method = env->GetStringUTFChars(method ,NULL);//转换成 char *

    LOGI("the name from Request method is %s", c_method);

    jfieldID urlID = env->GetFieldID(request_cls,"url","Ljava/lang/String;");
    jstring requestUrl = (jstring)env->GetObjectField(obj_request , urlID);//获得属性值
    const char * c_url = env->GetStringUTFChars(requestUrl ,NULL);//转换成 char *

    LOGI("the name from Request url is %s", c_url);



    jclass response_cls = env->FindClass("retrofit/client/Response"); //得到Response类的引用

    //获得得该类型的构造函数  函数名为 <init> 返回类型必须为 void 即 V
    jmethodID constrocMID = env->GetMethodID(response_cls,"<init>","(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Lretrofit/mime/TypedInput;)V");


    std::string responseUrl = "https://www.baidu.com/";
    jstring url =  env->NewStringUTF(responseUrl.c_str());
    std::string responseMsg = "OK";
    jstring msg =  env->NewStringUTF(responseMsg.c_str());

    jclass cls_ArrayList = env->FindClass("java/util/ArrayList");
    jmethodID construct = env->GetMethodID(cls_ArrayList,"<init>","()V");

    jobject obj_ArrayList = env->NewObject(cls_ArrayList,construct,"");
    jmethodID arrayList_add = env->GetMethodID(cls_ArrayList,"add","(Ljava/lang/Object;)Z");

    jclass cls_headers = env->FindClass("retrofit/client/Header");

    jmethodID construct_user = env->GetMethodID(cls_headers,"<init>","(Ljava/lang/String;Ljava/lang/String;)V");

    jstring header = env->NewStringUTF("Cache-Control");
    jstring value = env->NewStringUTF("no-cache");

    jobject obj_user = env->NewObject(cls_headers,construct_user,header,value);


    env->CallBooleanMethod(obj_ArrayList,arrayList_add,obj_user);


    jclass cls_TypedInput = env->FindClass("com/com/tangyc/retrofit/client/moni/SoTypedInput");

    jmethodID construct_TypedInput = env->GetMethodID(cls_TypedInput,"<init>","(Ljava/lang/String;JLjava/io/InputStream;)V");

    jstring mimeType = env->NewStringUTF("application/json");
    jclass cls_byteArrayInputStream=env->FindClass("java/io/ByteArrayInputStream");
    jmethodID construct_byteArrayInputStream=env->GetMethodID(cls_byteArrayInputStream,"<init>","([B)V");
    jstring str = env->NewStringUTF("{\n"
                                            "error: false,\n"
                                            "results: [ ]\n"
                                            "}");
    char*   rtn   =   NULL;
    jclass   clsstring   =   env->FindClass("java/lang/String");
    jstring   strencode   =   env->NewStringUTF("GB2312");
    jmethodID   mid   =   env->GetMethodID(clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
    jbyteArray   barr =   (jbyteArray)env->CallObjectMethod(str,mid,strencode);

    jobject  byteArrayInputStream_obj = env->NewObject(cls_byteArrayInputStream,construct_byteArrayInputStream,barr);

    jobject typedInput_obj = env->NewObject(cls_TypedInput,construct_TypedInput,mimeType,39,byteArrayInputStream_obj);
    jobject response_ojb = env->NewObject(response_cls,constrocMID,url,200,msg, obj_ArrayList,typedInput_obj);  //构造一个对象，调用该类的构造函数，并且传递参数

    return response_ojb;
}


