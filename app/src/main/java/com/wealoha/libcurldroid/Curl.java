package com.wealoha.libcurldroid;

import java.util.List;

import android.util.Log;

import com.wealoha.libcurldroid.CurlOpt.OptFunctionPoint;
import com.wealoha.libcurldroid.CurlOpt.OptLong;
import com.wealoha.libcurldroid.CurlOpt.OptObjectPoint;
import com.wealoha.libcurldroid.easy.MultiPart;

/**
 * Curl Jni Wrapper</br>
 * 
 * Usage: See MainActivity in demo project<br/>
 * 
 * 
 * Curl object isn't thread safe, DO NOT share one Curl instant through multiple threads.
 * 
 * @author javamonk
 * @createTime 2015-01-29 12:39:39
 * @see http://curl.haxx.se/libcurl/c/
 */
public class Curl {

	//可以参看CURL  https://curl.haxx.se/libcurl/c/
	
	private static final String TAG = Curl.class.getSimpleName();

	// 相当于session 标识

	private long handle;

	static {
		System.loadLibrary("curldroid");
	}
	/**
	 * 初始化网络  对应CURL的
	 * @param flags 初始化Flags
	 * @return 初始化后返回结果码 看是否返回成功
	 */
	private native static int curlGlobalInitNative(int flags);

	/**
	 * 结束清除网络
	 */
	private native static void curlGlobalCleanupNative();

	/**
	 * 网络请求初始化获取网络唯一标识  //对应curl框架 curl_easy_init()接口
	 * @return handle 网络请求的唯一标识 方便下次直接使用handle去调用
	 */
	private native long curlEasyInitNative();

	/**
	 * 初始化 请求的配置
	 * @param handle 请求的唯一标识
	 * @param opt    请求的类型  比如是请求的方式，请求的URL，设置代理等 使用规定int数值代替
	 * @param value  请求的数值 比如 GET，POST ，URL，代理参数数值等
	 * @return 请求结果码 看请求设置是否正确
	 */

	private native int curlEasySetoptLongNative(long handle, int opt, long value);

	/**
	 * 获取Response响应头信息或者Reponse响应Body的信息
	 * @param handle 请求的唯一标识
	 * @param  opt     请求的类型     用来区分响应头信息还是响应Body信息的
	 * @param callback  Response 响应头的回调
	 * @return 请求结果码 看请求设置是否正确
	 */
	private native int curlEasySetoptFunctionNative(long handle, int opt, Callback callback);
	/**
	 * 初始化 请求的配置
	 * @param handle 请求的唯一标识
	 * @param opt    请求的类型  比如是请求的方式，请求的URL，设置代理等 使用规定int数值代替
	 * @param value  请求的数值 比如 GET，POST ，URL，代理参数数值等
	 * @return 请求结果码 看请求设置是否正确
	 */

	private native int curlEasySetoptObjectPointBytesNative(long handle, int opt, byte[] value);
	/**
	 * 初始化 请求的配置
	 * @param handle 请求的唯一标识
	 * @param opt    请求的类型  比如是请求的方式，请求的URL，设置代理等 使用规定int数值代替,普通的表单请求
	 * @param value  请求的数值 比如 GET，POST ，URL，代理参数数值等
	 * @return 请求结果码 看请求设置是否正确
	 */
	private native int curlEasySetoptObjectPointNative(long handle, int opt, String value);
	/**
	 * 初始化 请求的配置
	 * @param handle 请求的唯一标识
	 * @param opt    请求的类型  请求头
	 * @param value  请求的数值  请求头进行的数据封装成数组形式，value 和key 封装成一个字符串
	 * @return 请求结果码 看请求设置是否正确
	 */
	private native int curlEasySetoptObjectPointArrayNative(long handle, int opt, String[] value);


	/**
	 * 发起网络请求
	 * @param handle 请求的唯一标识
	 * @return 请求结果码
	 */
	private native int curlEasyPerformNavite(long handle);
	/**
	 * post表单请求
	 * @param handle 唯一标识
	 * @param multiArray 请求的Form表单的 键值对 之所以用MultiPart主要是Boay请求体有可能不是以字符串形式体现的 ,可以以byte[]字节体现 //不包含普通的表单请求
	 * @return 请求结果码
	 */

	private native int setFormdataNative(long handle, MultiPart[] multiArray);


	/**
	 *
	 * 安全的结束本次请求
	 * @param handle  此次请求的唯一标识  //对应curl框架  curl_easy_cleanup()
	 */

	private native void curlEasyCleanupNative(long handle);



	public interface Callback {}

	public interface WriteCallback extends Callback {
		/**
		 * Called when data received from peer (for example: header, body)
		 *
		 * @param data
		 * @return the number of bytes actually taken care of.
		 * @see http://curl.haxx.se/libcurl/c/CURLOPT_WRITEFUNCTION.html
		 */
		public int readData(byte[] data);
	}

	public interface ReadCallback extends Callback {
		/**
		 * Called when data need send to peer (for example: header, form)
		 *
		 * @param data the buffer to fill
		 * @return the actual number of bytes that it stored in that memory area.
		 * @see http://curl.haxx.se/libcurl/c/CURLOPT_READFUNCTION.html
		 */
		public int writeData(byte[] data);
	}
	
	private static Object CLEANUP = new Object() {
		
		@Override
		protected void finalize() throws Throwable {
			if (INIT) {
				Log.i(TAG, "curlGlobalCleanup");
				curlGlobalCleanupNative();
			}
		}
		
	};
	
	private static boolean INIT = false;
	

	
	
	public Curl() {
		if (!INIT) {
			curlGlobalInit(CurlConstant.CURL_GLOBAL_DEFAULT);
		}
	}
	
	public static void curlGlobalInit(int flags) {
		if (INIT) {
			return;
		}
		
		CurlCode code = CurlCode.fromValue(curlGlobalInitNative(flags));
		if (code != CurlCode.CURLE_OK) {
			throw new IllegalStateException("curlGlobalInit fail: " + code);
		}
		INIT = true;
	}
	

	
	public void curlEasyInit() throws CurlException  {
		Log.v(TAG, "curlEastInit");
		handle = curlEasyInitNative();
		//-266477032
		if (handle == 0) {
			throw new CurlException("curl init native fail");
		}
	}
	

	
	public void curlEasyCleanup() {
		Log.v(TAG, "curlEastCleanup: " + handle);
		if (handle != 0) {
			curlEasyCleanupNative(handle);
		}
		handle = 0;
	}
	

	
	/**
	 * 
	 * @param opt {@link OptLong}
	 * @param value
	 * @return
	 */
	public CurlCode curlEasySetopt(OptLong opt, long value) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
		return CurlCode.fromValue(curlEasySetoptLongNative(handle, opt.getValue(), value));
	}
	

	
	public CurlCode curlEasySetopt(OptFunctionPoint opt, WriteCallback callback) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + callback);
		return CurlCode.fromValue(curlEasySetoptFunctionNative(handle, opt.getValue(), callback));
	}
	

	
	public CurlCode curlEasySetopt(OptObjectPoint opt, String value) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
		return CurlCode.fromValue(curlEasySetoptObjectPointNative(handle, opt.getValue(), value));
	}
	
	

	
	public CurlCode curlEasySetopt(OptObjectPoint opt, byte[] value) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
		return CurlCode.fromValue(curlEasySetoptObjectPointBytesNative(handle, opt.getValue(), value));
	}
	

	
	public CurlCode curlEasySetopt(OptObjectPoint opt, String[] values) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + values);
		for(int i=0;i<values.length;i++) {
			Log.i(TAG, "curlEastSetopt: "+values[i]);
		}
		return CurlCode.fromValue(curlEasySetoptObjectPointArrayNative(handle, opt.getValue(), values));
	}

	
	/**
	 * if set multiple times, previous form will be cleared!
	 * 
	 * @param multiParts
	 * @return
	 */
	public CurlFormadd setFormdata(List<MultiPart> multiParts) {
		if (multiParts != null && multiParts.size() > 0) {
			Log.e("DEBUG","FORMDATA----------------------------------------");
			return CurlFormadd.fromValue(setFormdataNative(handle, multiParts.toArray(new MultiPart[multiParts.size()])));
		} else {
			return CurlFormadd.CURL_FORMADD_NULL;
		}
	}

	public CurlCode curlEasyPerform() {
		Log.v(TAG, "curlEasyPerform");
		return CurlCode.fromValue(curlEasyPerformNavite(handle));
	}

}
