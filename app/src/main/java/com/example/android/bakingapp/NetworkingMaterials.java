package com.example.android.bakingapp;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class NetworkingMaterials {
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie";
    private AsyncHttpClient client;
    private Context mContext;
    private NetworkingComplete complete;

    public NetworkingMaterials(Context mContext,NetworkingComplete complete){
        client = new AsyncHttpClient();
        this.mContext = mContext;
        this.complete = complete;
    }

    public void myNetworking (String parameter) {

        client.get(BASE_URL+parameter,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                complete.completedTask(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(mContext,"Status Code : "+statusCode,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }

        });
    }


    public interface NetworkingComplete{
        void completedTask(JSONObject response);
    }
}
