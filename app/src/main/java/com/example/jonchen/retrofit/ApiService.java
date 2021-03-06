package com.example.jonchen.retrofit;

import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.model.entity.DailyNewspaper;
import com.example.jonchen.model.entity.Doctor;
import com.example.jonchen.model.entity.Hospital;
import com.example.jonchen.model.entity.KmResult;
import com.example.jonchen.model.entity.Movie;
import com.example.jonchen.model.entity.NewsDetail;
import com.example.jonchen.model.entity.RecommendDoctors;
import com.example.jonchen.model.entity.Result;
import com.example.jonchen.model.entity.ServerChatMessage;
import com.example.jonchen.model.entity.ShareInfo;
import com.example.jonchen.model.entity.UploadMessage;
import com.example.jonchen.model.entity.WatchInfo;
import com.example.jonchen.model.entity.ZhiHuResult;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by chenxujun on 2016/7/12.
 */
public interface ApiService {

    @GET("http://news-at.zhihu.com/api/4/news/{id}")
    Observable<NewsDetail> getNewsDetail(@Path("id") String id);

    //String BASE_DOUBAN_API = "https://api.douban.com/";
    //String HK_BASE_URL = "https://patientapi.hk515.com/";

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

    @POST("RecommendDoctorsList")
    @FormUrlEncoded
    Call<Result<List<Doctor>>> queryDoctors(@FieldMap Map<String, Object> map);

    @POST("RecommendDoctorsList")
    @FormUrlEncoded
    Call<ResponseBody> queryDoc(@FieldMap Map<String, Object> map);

    @POST("RecommendDoctorsList")
    @FormUrlEncoded
    Observable<ResponseBody> originQueryDoctors(@FieldMap Map<String, Object> map);

    @POST("RecommendDoctorsList")
    @FormUrlEncoded
    Observable<Result<RecommendDoctors>> rxQueryDoctors(@FieldMap Map<String, Object> map);

    @GET
    @Streaming
    Observable<ResponseBody> update(@Url String url);

    //start=0&count=10"
    @GET("v2/movie/top250")
    Call<Movie> getMovie(@Query("start") int start, @Query("end") int end);

    @POST("PreTreatment/GetHospitalDetail/601")
    Observable<ResponseBody> getHosInfo();

    @POST("PreTreatment/GetHospitalDetail/601")
    Observable<Result<Hospital>> rxGetHosInfo();

    @POST("Home/QureyHomeInfo")
    @FormUrlEncoded
    Observable<Result<List<ShareInfo>>> getShareInfo(@FieldMap Map<String, Object> map);

    @POST("Home/QureyHomeInfo")
    @FormUrlEncoded
    Observable<ResponseBody> getShare(@FieldMap Map<String, Object> map);

    @GET("member/getbindDeviceWithWearersInfo/{account}?_type=json")
    Observable<KmResult<List<WatchInfo>>> getWatchList(@Path("account") String account);

    @POST("voice/upload")
    Observable<ResponseBody> rxUploadVoiceMsg(@Body UploadMessage message);

    @GET("voice/getVoiceList/{account}/{imei}/{timestamp}")
    Observable<KmResult<List<ServerChatMessage>>>
    getVoiceList(@Path("account") String account
            , @Path("imei") String imei
            , @Path("timestamp") long timestamp);


    @GET("news/latest")
    Observable<ResponseBody> rxGetZhiHuNews();

    @GET("http://news-at.zhihu.com/api/4/news/latest")
    Observable<DailyBean> rxModelGetZhiHuNews();



    @GET("news/latest")
    Call<DailyBean> getDaily();

    @GET("news/latest")
    Observable<ZhiHuResult<List<DailyNewspaper>>> getZhiHuNews();

    @GET("news/latest")
    Observable<DailyBean> getDailyBean();
}
