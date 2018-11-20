package charko.tester01.com.imageviewertop10.retrofit;

import charko.tester01.com.imageviewertop10.Content;
import charko.tester01.com.imageviewertop10.PageContent;
import charko.tester01.com.imageviewertop10.ResponseItem;
import charko.tester01.com.imageviewertop10.User;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("user/alreadyjoin")
    Call<String> getUser(@Body User user);

    @POST("content/index/{nNowPage}")
    Call<PageContent> getAllContent(@Path("nNowPage") int nNowPage);

    @POST("content/one/{nContentID}")
    Call<Content> getContentOne(@Path("nContentID") int nContentID);

    @POST("content/")
    Call<ResponseItem> setContent(@Body Content content);

    @Multipart
    @POST("content/upload/{nUserID}") //@Part("description") RequestBody description,
    Call<ResponseItem> upload(@Path("nUserID") int nUserID, @Part MultipartBody.Part image);

}
