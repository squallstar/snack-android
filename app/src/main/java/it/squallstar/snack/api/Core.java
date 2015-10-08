package it.squallstar.snack.api;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import it.squallstar.snack.BuildConfig;
import it.squallstar.snack.models.Article;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * Created by nicholas on 08/10/2015.
 */
public class Core {

    private static ApiInterface sApiService;
    private static Core instance;

    private static String API_URL = "http://fragments.me";

    public static Core Current() {
        if (instance == null) instance = new Core();
        return instance;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static ApiInterface getApiClient() {
        if (sApiService == null) {
            GsonConverter converter = new GsonConverter(new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create());

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .setConverter(converter)
                    .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.HEADERS : RestAdapter.LogLevel.NONE)
                    .setLog(new AndroidLog("API"))
                    .setRequestInterceptor(new RequestInterceptor() {

                        @Override
                        public void intercept(RequestFacade request) {
                            request.addHeader("X-Token", "andr00id");
                            request.addHeader("User-Agent", "Snack-Android");
                        }
                    })
                    .build();

            Log.i("API", "REST Service created");

            sApiService = restAdapter.create(ApiInterface.class);
        }

        return sApiService;
    }

    public interface ApiInterface {
        @GET("/api/articles")
        void getArticles(Callback<ArrayList<Article>> callback);
    }
}
