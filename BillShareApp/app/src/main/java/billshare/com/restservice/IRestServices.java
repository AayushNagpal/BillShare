package billshare.com.restservice;

import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;


public interface IRestServices {
    @POST("/user/register")
    Call<ResponseStatus> register(@Body User user);
    @POST("/user/login")
    Call<ResponseStatus> login(@Body User user);

}