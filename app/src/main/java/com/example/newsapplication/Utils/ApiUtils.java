package com.example.newsapplication.Utils;

import android.content.Context;

import com.example.newsapplication.R;
import com.example.newsapplication.Service.NewsRequestInterface;
import com.example.newsapplication.Service.RetrofitClient;

public class ApiUtils
{
    private static String BASE_URL;
    private Context context;

    public ApiUtils(Context context)
    {
        this.context=context;
        BASE_URL=context.getString(R.string.BASE_URL);
    }
    public static NewsRequestInterface getNewsRequestInterface()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsRequestInterface.class);
    }
}
