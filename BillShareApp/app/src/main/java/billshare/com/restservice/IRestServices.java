package billshare.com.restservice;

import billshare.com.model.Group;
import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import billshare.com.utils.GroupsList;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


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

}
