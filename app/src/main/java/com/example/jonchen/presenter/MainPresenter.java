package com.example.jonchen.presenter;

import android.os.Environment;

import com.example.jonchen.R;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.model.entity.DailyNewspaper;
import com.example.jonchen.mvpview.MainView;
import com.example.jonchen.retrofit.ApiService;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.OkHttpUtils;
import com.example.jonchen.retrofit.RequestParams;
import com.example.jonchen.retrofit.RetrofitMethods;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.RxUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chenxujun on 2016/7/1.
 */
public class MainPresenter extends BasePresenter<MainView> {

    private Subscription subscribe;
    //private MainView mView;
    private Subscription subscription;


    public MainPresenter(MainView mView) {
        super(mView);
    }

    public static final String PATH = Environment.getExternalStorageDirectory() + "/kmytj/";

    /**
     * 文件名
     */
    private final String saveFileName = PATH + "heiheihei.apk";


    private void getMovie() {
    /*Gson gson = new GsonBuilder().serializeNulls()
            .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiService.BASE_DOUBAN_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    retrofit.create(ApiService.class).getMovie(0, 10).enqueue(new Callback<Movie>() {
        @Override
        public void onResponse(Call<Movie> call, Response<Movie> response) {

            Movie movie = response.body();
            List<Movie.SubjectsBean> subjects = movie.getSubjects();
        }

        @Override
        public void onFailure(Call<Movie> call, Throwable t) {

        }
    });*/
    }

    public void click() {
        RxUtils.get().unSubscribe();
    }


    public void getSlidingMenuData() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arrayList.add(String.valueOf(i));
        }
        mView.createSlidingMenuView(arrayList);
    }

    public void getDailyInfo() {
        mView.showLoadingPage(R.id.mainRL);
        RetrofitMethods.modelCommonRequest(getApiService().rxModelGetZhiHuNews(), new CustomObserver<DailyBean>(mView) {
            @Override
            public void doOnNext(DailyBean dailyBean) {
                mView.refreshPage(dailyBean.getStories());
            }
        });

        /*mView.showLoadingPage();
        RetrofitMethods retrofitMethods = new RetrofitMethods(RetrofitMethods.ZH_BASE_URL);
        retrofitMethods.spCommonRequest(RetrofitMethods.getSpApiService().getZhiHuNews(), new CustomObserver<List<DailyNewspaper>>(mView) {
            @Override
            public void doOnNext(List<DailyNewspaper> dailyNewspapers) {
                mView.refreshPage(dailyNewspapers);
            }
        });
*/

       /* HashMap<String, Object> params = new HashMap<>();
        params.put("StartIndex", 0);
        params.put("EndIndex", endIndex);
        params.put("CityId", "2430");

        RetrofitMethods.hkCommonRequest(RetrofitUtils.getApiService().rxQueryDoctors(params)
                , new CustomObserver<RecommendDoctors>(mContext) {
                    @Override
                    public void doOnNext(RecommendDoctors recommendDoctors) {
                        mView.refreshDocList(recommendDoctors.getRecommendDoctors());
                    }
                });*/

        /*RetrofitMethods.getApiService().queryDoc(params).enqueue(new RespCallback() {
            @Override
            public void onSuccessResp(String responseString, String data) {
                ToastUtils.show(responseString);
            }

            @Override
            public void onFailureResp(String responseString, String data) {
                ToastUtils.show(responseString);
            }

            @Override
            public void onFail(Throwable t) {

            }
        });*/



        /*RetrofitMethods.commonRequest(getApiService().rxGetHosInfo(), new CustomObserver<Hospital>(context) {
            @Override
            public void doOnNext(Hospital hospital)  {
                ToastUtils.show(hospital.getHospitalName());
                LogUtils.e(hospital.getHospitalName());
            }
        });*/

        //RetrofitMethods.commonRequest(RetrofitUtils.getApiService().rxGetHosInfo(), subscriber);



     /*   RetrofitMethods.commonRequest(RetrofitUtils.getApiService().rxGetHosInfo(), new CustomSubscriber<Hospital>(context) {
        });*/


        /*RetrofitMethods.commonRequest(
                RetrofitUtils.getApiService().rxQueryDoctors(params),
                new Subscriber<List<Doctor>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        e.getMessage();
                    }

                    @Override
                    public void onNext(List<Doctor> doctors) {
                        ToastUtils.show(doctors.get(1).getDoctorName());
                    }
                }
        );*/

    }

    private void gson() {
       /* String jsonString = "{name:怪盗kidou,age:24}";
        Gson gson = new Gson();
        People people = gson.fromJson(jsonString, People.class);

        LogUtils.i("complete");

        Call login = retrofit.create(ApiService.class).Login(params);
        login.enqueue(new RespCallback() {
            @Override
            public void onSuccessResp(Call<?> call, JSONObject response) {

            }

            @Override
            public void onFailureResp(Call<?> call, JSONObject response) {

            }

            @Override
            public void onFail(Call<?> call, Throwable response) {

            }
        });*/
    }

    private void kmLogin() {
        RequestParams params = new RequestParams();
        params.put("mobilePhone", "C840104238F");
        params.put("password", "C840104238F");
        params.put("phoneOsType", "A");
        params.put("pushInfo", "");
        params.put("userType", "1");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://218.17.23.74:8089/jkyh_app/").build();

      /*  retrofit.create(ApiService.class).Login(params).enqueue(new RespCallback() {
            @Override
            public void onSuccessResp(Call<?> call, JSONObject response) {
                String dataString = response.optString("resultData");
                User user = new Gson().fromJson(dataString, User.class);
                LogUtils.i("complete");
            }

            @Override
            public void onFailureResp(Call<?> call, JSONObject response) {

            }

            @Override
            public void onFail(Call<?> call, Throwable response) {

            }
        });*/
    }

    private void update() {

        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        final File saveFile = new File(saveFileName);



      /*  RetrofitMethods.flatRequest(RetrofitUtils.getApiService()
                .getUpdateMessage("kmhc-apk-service/apk/verCheck"),
                new RetrofitMethods.flatCallback<ResponseBody>() {

                    private String downloadUrl;

                    @Override
                    public Observable onFlat(ResponseBody responseBody) {
                        try {
                            String res = responseBody.string();
                            downloadUrl = new JSONObject(res).optString("msg");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return RetrofitUtils.getApiService().update(downloadUrl);
                    }
                }, new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }
                });
*/

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.25.225.5:8090/")
                .client(OkHttpUtils.getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        subscribe = retrofit.create(ApiService.class).getUpdateMessage("kmhc-apk-service/apk/verCheck")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<ResponseBody, Observable<ResponseBody>>() {

                    private String downloadUrl;

                    @Override
                    public Observable<ResponseBody> call(ResponseBody responseBody) {
                        try {
                            String res = responseBody.string();
                            downloadUrl = new JSONObject(res).optString("msg");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return retrofit.create(ApiService.class).update(downloadUrl);
                    }
                })
                .subscribe(new Action1<ResponseBody>() {

                    private InputStream inputStream;
                    private FileOutputStream fileOutputStream;

                    @Override
                    public void call(ResponseBody responseBody) {

                        try {
                            long total = responseBody.contentLength();
                            long sum = 0;

                            inputStream = responseBody.byteStream();
                            fileOutputStream = new FileOutputStream(saveFile);

                            byte[] buf = new byte[1024];
                            int len;

                            long firstTime = System.currentTimeMillis();

                            while ((len = inputStream.read(buf)) != -1) {
                                sum = sum + len;
                                int percent = (int) (((float) sum / total) * 100);
                                LogUtils.e(String.valueOf(percent));
                                fileOutputStream.write(buf, 0, len);
                            }

                            long secondTime = System.currentTimeMillis();
                            long duration = secondTime - firstTime;
                            LogUtils.e("waste:---------" + String.valueOf(duration));

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        // RxUtils.get().addList(subscribe);


       /* retrofit.create(ApiService.class).update().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                new Thread(new Runnable() {

                    FileOutputStream fileOutputStream;
                    InputStream inputStream;

                    @Override
                    public void run() {
                        try {
                            long total = response.body().contentLength();
                            long sum = 0;

                            inputStream = response.body().byteStream();
                            fileOutputStream = new FileOutputStream(saveFile);

                            byte[] buf = new byte[1024];
                            int len;

                            long firstTime = System.currentTimeMillis();

                            while ((len = inputStream.read(buf)) != -1) {
                                sum = sum + len;
                                int percent = (int) (((float) sum / total) * 100);
                                LogUtils.e(String.valueOf(percent));
                                fileOutputStream.write(buf, 0, len);
                            }

                            long secondTime = System.currentTimeMillis();
                            long duration = secondTime - firstTime;
                            LogUtils.e("waste:---------" + String.valueOf(duration));

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.getMessage();
            }
        });*/
    }


   /* private void gitDemo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").build();
        ApiService repo = retrofit.create(ApiService.class);
        Call<ResponseBody> call = repo.getResponse("square", "retrofit");
        call.enqueue(new RespCallback() {
            @Override
            public void onSuccessResp(ResponseBody body, String data) {
                ToastUtils.show(data);
            }

            @Override
            public void onFailureResp(ResponseBody body, String data) {

            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
*/
   /* private void hkLogin() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LoginName", "18681537809");
        params.put("PassWord", "361753");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://patientapi.hk515.com/")
                .client(OkHttpUtils.getHttpClient())
                .build();

        retrofit.create(ApiService.class).hkLogin(params).enqueue(new RespCallback() {
            @Override
            public void onSuccessResp(ResponseBody body, String data) {
                ToastUtils.show(data);
            }

            @Override
            public void onFailureResp(ResponseBody body, String data) {

            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }*/
}
