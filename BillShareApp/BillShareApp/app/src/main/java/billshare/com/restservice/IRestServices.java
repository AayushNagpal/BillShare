package billshare.com.restservice;

import billshare.com.model.Group;
import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;


public interface IRestServices {
    @POST("/user/register")
    Call<ResponseStatus> register(@Body User user);

    @POST("/user/login")
    Call<ResponseStatus> login(@Body User user);

    @POST("/user/list")
    Call<ResponseStatus> users(@Query("id") String id);

    @POST("/group/save")
    Call<Group> saveGroup(@Body Group group);

}
