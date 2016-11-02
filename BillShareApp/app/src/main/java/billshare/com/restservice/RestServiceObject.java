package billshare.com.restservice;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;


public class RestServiceObject {
    private static Context context1;

<<<<<<< HEAD
    private static final String BASE_URL = "http://192.168.0.101:8080";
=======
    private static final String BASE_URL = "http://192.168.0.21:8080";
>>>>>>> 53b57b7ebf2afcfe63ce985ac80391b510b5399f

    public static IRestServices getiRestServicesObject(Context context) {
        context1 = context;

        ObjectMapper objectMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        retrofit.client().setConnectTimeout(10, TimeUnit.SECONDS);
        return retrofit.create(IRestServices.class);
    }

}
