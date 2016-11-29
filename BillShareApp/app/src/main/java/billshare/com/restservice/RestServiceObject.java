package billshare.com.restservice;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class RestServiceObject {
    private static Context context1;

    private static final String BASE_URL = "http://192.168.0.9:8080";

    public static IRestServices getiRestServicesObject(Context context) {
        context1 = context;

        ObjectMapper objectMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

       // retrofit.client().setConnectTimeout(10, TimeUnit.SECONDS);
        return retrofit.create(IRestServices.class);
    }

}
