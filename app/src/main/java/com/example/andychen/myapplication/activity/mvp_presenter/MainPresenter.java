package com.example.andychen.myapplication.activity.mvp_presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.View;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.activity.SecondActivity;
import com.example.andychen.myapplication.activity.activity.ThirdActivity;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.bean.Result;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.retrofit.ApiService;
import com.example.andychen.myapplication.activity.retrofit.BeanRespCallBack;
import com.example.andychen.myapplication.activity.retrofit.OkHttpUtils;
import com.example.andychen.myapplication.activity.retrofit.RequestParams;
import com.example.andychen.myapplication.activity.retrofit.RespCallback;
import com.example.andychen.myapplication.activity.utils.IntentUtils;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.NullStringToEmptyAdapterFactory;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andychen on 2016/7/1.
 */
public class MainPresenter extends BasePresenter {

    private Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    public static final String PATH = Environment.getExternalStorageDirectory() + "/kmytj/";

    /**
     * 文件名
     */
    private final String saveFileName = PATH + "heiheihei.apk";

    @Override
    public void performOnClick(View v) {
        super.performOnClick(v);
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivityLeftIn((Activity) context, SecondActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn((Activity) context, ThirdActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
            case R.id.iv:
               /* Activity activity = (Activity) this.context;
                activity.startActivityForResult(new Intent(activity,CaptureActivity.class),0);*/


              /*  OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build();

                Integer i = 5;
                FormBody formBody = new FormBody.Builder().add("", i.toString()).build();

                Request.Builder request = new Request.Builder().post(formBody);
                Request request1 = request.build();*/


              /*  hkLogin();


                gitDemo();

                update();

                kmLogin();

                gson();*/


                HashMap<String, Object> params = new HashMap<>();
               /* params.put("cityId", "2157");
                params.put("districtId", 0);
                params.put("hospitalService", 0);
                params.put("isHot", 1);
                params.put("StartIndex", 0);
                params.put("EndIndex", 10);
                params.put("OrderBy", 0);
                params.put("HospitalType", -1);*/


                params.put("HospitalId", "601");
                params.put("IsShowAvailableCount", true);
                params.put("StartIndex", 0);
                params.put("EndIndex", 9);
                params.put("OrderBy", 0);
                params.put("DoctorType", "2");
                params.put("ProfessionDepartmentId", "");

                //params.put("ProfessionDepartmentId", professionDepartmentId);
                params.put("CityId", "2157");
                params.put("SchedulingDate", "");
                params.put("DistrictId", "0");


                update();
                //hkLogin();
                //gitDemo();
               /* Gson gson = new GsonBuilder().serializeNulls()
                        .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://patientapi.hk515.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(OkHttpUtils.getHttpClient())
                        .build();

                Call<Result<List<Doctor>>> resultCall = retrofit.create(ApiService.class).queryDoctors(params);

                ((BaseActivity)context).setCall(resultCall);

                resultCall.enqueue(new BeanRespCallBack<List<Doctor>>() {
                    @Override
                    public void onSuccessResp(Call<Result<List<Doctor>>> call, List<Doctor> response) {
                        response.size();
                        String doctorName = response.get(0).getDoctorName();
                        ToastUtils.show(doctorName);
                    }

                    @Override
                    public void onFailureResp(Call<Result<List<Doctor>>> call, List<Doctor> response) {

                    }

                    @Override
                    public void onFail(Call<Result<List<Doctor>>> call, Throwable response) {

                    }
                });
*/
              /*  retrofit.create(ApiService.class).getHotHos(params).enqueue(new RespCallback() {
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

                break;
        }
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
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.25.225.5:8090/")
                .client(OkHttpUtils.getHttpClient())
                .build();


        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        final File file1 = new File(saveFileName);


        retrofit.create(ApiService.class).update().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,final Response<ResponseBody> response) {
                new Thread(new Runnable() {

                    FileOutputStream fileOutputStream;
                    InputStream inputStream;

                    @Override
                    public void run() {
                        try {
                            long total = response.body().contentLength();
                            long sum = 0;

                            inputStream = response.body().byteStream();
                            fileOutputStream = new FileOutputStream(file1);

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

            }
        });


       /* retrofit.create(ApiService.class).update().enqueue(new RespCallback() {

            @Override
            public void onSuccessResp(final ResponseBody body, String data) {

                new Thread(new Runnable() {

                    FileOutputStream fileOutputStream;
                    InputStream inputStream;

                    @Override
                    public void run() {
                        try {
                            long total = body.contentLength();
                            long sum = 0;

                            inputStream = body.byteStream();
                            fileOutputStream = new FileOutputStream(file1);

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
            public void onFailureResp(ResponseBody body, String data) {

            }

            @Override
            public void onFail(Throwable t) {
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
