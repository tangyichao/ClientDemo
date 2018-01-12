package com.tangyc.clientdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wealoha.libcurldroid.CurlHttp;
import com.wealoha.libcurldroid.retrofit.RetrofitCurlClient;
import com.wealoha.libcurldroid.third.CurlHttpCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedFile;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author tangyichao
 */
public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    private final  static String LOG=MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retrofit1.9的使用 结合RxJava的使用 重写Client


        //Retrofit2.3的使用 结合RxJava的使用 使用OkHttp3网络请求
//        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gank.io/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
//        MyService2 retrofit2Service=retrofit.create(MyService2.class);
//        Observable<GithubTest> retrofit2Observable= retrofit2Service.listRepos("iOS");
//        load(retrofit2Observable);
        final ImageView imageView=findViewById(R.id.iv);

       final RetrofitCurlClient client = new RetrofitCurlClient()
                .curlCallback(new CurlHttpCallback() {

                    @Override
                    public void afterInit(CurlHttp curlHttp, String url) {
                        curlHttp.setTimeoutMillis(1000 * 20);
                        curlHttp.setConnectionTimeoutMillis(1000 * 10);
                        curlHttp.addHeader("Accept-Encoding", "gzip");
                    }});

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RestAdapter adapter=new RestAdapter.Builder().setClient(client).setEndpoint("http://gank.io").setConverter(new GsonConverter(new Gson())).setLogLevel(RestAdapter.LogLevel.BASIC).build();
                MyService service= adapter.create(MyService.class);
                Observable<GithubTest> observable= service.listRepos("Android");
                load(observable);
            }
        });
        findViewById(R.id.btn_get_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RestAdapter adapter=new RestAdapter.Builder().setClient(client).setEndpoint("http://mpic.tiankong.com").setLogLevel(RestAdapter.LogLevel.BASIC).build();
                MyService service= adapter.create(MyService.class);
                Observable<Response> observable=service.imageRepos();
                observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG, "Retrofit--->"+e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        try {
                            InputStream in=response.getBody().in();
                            Bitmap bitmap= BitmapFactory.decodeStream(in);
                            imageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


//        RestAdapter adapter1=new RestAdapter.Builder().setClient(client).setEndpoint("http://116.196.121.20").setLogLevel(RestAdapter.LogLevel.BASIC).build();
//        GetService postService= adapter1.create(GetService.class);
//        Observable<Response> postObservable= postService.listRepos();
//        postObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response>() {
//            @Override
//            public void onCompleted() {
//                Log.i(LOG,"Retrofit--->"+"onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(LOG, "Retrofit--->"+e.getMessage());
//            }
//
//            @Override
//            public void onNext(Response response) {
//                try {
//                    InputStream in=response.getBody().in();
//                    Log.i(LOG, "Retrofit--->"+inputStream2String(in));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestAdapter postAdapter=new RestAdapter.Builder().setClient(client).setEndpoint("http://japi.juhe.cn").setLogLevel(RestAdapter.LogLevel.BASIC).build();
                PostService postService= postAdapter.create(PostService.class);
                Observable<Response> postObservable= postService.listRepos("","","","","1da3c9447e3b07beb0b78b19f5b5f229");
                postObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG, "Retrofit--->"+e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        try {
                            InputStream in=response.getBody().in();
                            Toast.makeText(MainActivity.this,"post"+inputStream2String(in),Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

       findViewById(R.id.btn_post_form).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RestAdapter formAdapter=new RestAdapter.Builder().setClient(client).setEndpoint("http://japi.juhe.cn").setLogLevel(RestAdapter.LogLevel.BASIC).build();
               FormService formService= formAdapter.create(FormService.class);
               Observable<Response> formObservable=formService.listRepos("","","","","1da3c9447e3b07beb0b78b19f5b5f229");
               formObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response>() {
                   @Override
                   public void onCompleted() {
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e(LOG, "Retrofit---> Form "+e.getMessage());
                   }
                   @Override
                   public void onNext(Response response) {
                       try {
                           InputStream in=response.getBody().in();
                           Toast.makeText(MainActivity.this,inputStream2String(in),Toast.LENGTH_LONG).show();

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               });
           }
       });
       findViewById(R.id.btn_post_form_map).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RestAdapter formAdapter=new RestAdapter.Builder().setClient(client).setEndpoint("http://japi.juhe.cn").setLogLevel(RestAdapter.LogLevel.BASIC).build();
               FormService formService= formAdapter.create(FormService.class);
               Map<String,String> map=new HashMap<>();
               map.put("key","1da3c9447e3b07beb0b78b19f5b5f229");
               Observable<Response> formObservable=formService.listRepos(map);
               formObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response>() {
                   @Override
                   public void onCompleted() {
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e(LOG, "Retrofit---> Form "+e.getMessage());
                   }

                   @Override
                   public void onNext(Response response) {
                       try {
                           InputStream in=response.getBody().in();
                           Toast.makeText(MainActivity.this,inputStream2String(in),Toast.LENGTH_LONG).show();

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               });
           }
       });
       findViewById(R.id.btn_post_Multipart).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RestAdapter multiparAdapter=new RestAdapter.Builder().setClient(client).setEndpoint("http://japi.juhe.cn").setLogLevel(RestAdapter.LogLevel.BASIC).build();
               MultipartService multipartService= multiparAdapter.create(MultipartService.class);
               String path=Environment.getExternalStorageDirectory().getPath()+"/test_20171021171543.png";
               File file=  new File(path);
               TypedFile typefile=new TypedFile("image/jpeg",file);
               Observable<Response> formObservable=multipartService.listRepos(typefile);
               formObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e(LOG, "Retrofit---> Form "+e.getMessage());
                   }

                   @Override
                   public void onNext(Response response) {
                       try {
                           InputStream in=response.getBody().in();
                           Toast.makeText(MainActivity.this,inputStream2String(in),Toast.LENGTH_LONG).show();

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               });
           }
       });


    }

    public String inputStream2String(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    private void load(Observable<GithubTest> observable) {
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GithubTest>() {
            @Override
            public void onCompleted() {
                Log.i(LOG,"Retrofit--->"+"onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.e(LOG, "Retrofit--->"+e.getMessage());
            }
            @Override
            public void onNext(GithubTest githubTest) {
                Log.i(LOG, "Retrofit--->"+githubTest.getResults().get(0).getDesc());
                Toast.makeText(MainActivity.this,githubTest.getResults().get(0).getDesc(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
