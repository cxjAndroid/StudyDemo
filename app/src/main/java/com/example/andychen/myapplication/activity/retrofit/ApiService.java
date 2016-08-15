package com.example.andychen.myapplication.activity.retrofit;

import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.bean.Result;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by andychen on 2016/7/12.
 */
public interface ApiService {

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> getResponse(@Path("owner") String owner, @Path("repo") String repo);

    @GET
    Call<ResponseBody> getUpdateMessage(@Url String url);

    @POST("app/user/login.do")
    @FormUrlEncoded
    Call<ResponseBody> Login(@FieldMap Map<String, Object> map);

    @POST("PatientUser/UserLogin")
    @FormUrlEncoded
    Call<ResponseBody> hkLogin(@FieldMap Map<String, Object> map);

    @POST("PreTreatment/QueryHospitals")
    @FormUrlEncoded
    Call<ResponseBody> getHotHos(@FieldMap Map<String, Object> map);

    @POST("PreTreatment/QueryDoctors")
    @FormUrlEncoded
    Call<Result<List<Doctor>>> queryDoctors(@FieldMap Map<String, Object> map);
}
