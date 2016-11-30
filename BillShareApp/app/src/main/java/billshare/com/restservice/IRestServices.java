package billshare.com.restservice;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import billshare.com.model.Group;
import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import billshare.com.utils.GroupsList;
import okhttp3.MultipartBody;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface IRestServices {
    @POST("/users/register")
    Call<ResponseStatus> register(@Body User user);

    @POST("/users/login")
    Call<ResponseStatus> login(@Body User user);

    @GET("/users/list")
    Call<ResponseStatus> users(@Query("id") String id);

    @POST("/groups/save")
    Call<Group> saveGroup(@Body Group group);

    @GET("/groups/list")
    Call<GroupsList> groups(@Query("id") String id);
    @POST("/groups/update")
    Call<Group> update(@Body Group group);
    @Multipart
    @POST("/groups/saveGroup")
    Call<Group> saveGroup(
            @Part MultipartBody.Part file);

}
