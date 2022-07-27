package com.getworld.wallpaperspapeldeparede;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit=null;
    public static final String API="563492ad6f9170000100000172514670a2054a2ebfa50758e66fc928";


    public static ApiInterface getApiInterface()
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
