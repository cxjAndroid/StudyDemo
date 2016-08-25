package com.example.andychen.myapplication.activity.retrofit;

import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.bean.Hospital;
import com.example.andychen.myapplication.activity.bean.Movie;
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
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by andychen on 2016/7/12.
 */
public interface ApiService {

    String BASE_DOUBAN_API = "https://api.douban.com/";

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> getResponse(@Path("owner") String owner, @Path("repo") String repo);

    @GET
    Observable<ResponseBody> getUpdateMessage(@Url String url);

    @GET
    Observable<ResponseBody> getPic(@Url String url);

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

    @POST("PreTreatment/QueryDoctors")
    @FormUrlEncoded
    Call<ResponseBody> queryDoc(@FieldMap Map<String, Object> map);

    @POST("PreTreatment/QueryDoctors")
    @FormUrlEncoded
    Observable<ResponseBody> originQueryDoctors(@FieldMap Map<String, Object> map);

    @POST("PreTreatment/QueryDoctors")
    @FormUrlEncoded
    Observable<Result<List<Doctor>>> rxQueryDoctors(@FieldMap Map<String, Object> map);

    @GET
    @Streaming
    Observable<ResponseBody> update(@Url String url);

    //start=0&count=10"
    @GET("v2/movie/top250")
    Call<Movie> getMovie(@Query("start") int start, @Query("end") int end);

    @POST("PreTreatment/GetHospitalDetail/601")
    Observable<ResponseBody> getHosInfo();

    @POST("PreTreatment/GetHospitalDetail/12312321321321")
    Observable<Result<Hospital>> rxGetHosInfo();




}
