package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

import static com.example.android.bakingapp.RecipiesGridAdapter.RECIPE_OBJECT;

public class MainActivity extends AppCompatActivity implements RecipiesGridAdapter.GridItemClickListener {

    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json" ;
    GridLayoutManager mLayoutManager;
    RecyclerView recipeGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeGrid = (RecyclerView)findViewById(R.id.recipe_grid);
        mLayoutManager = new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
        recipeGrid.setLayoutManager(mLayoutManager);

        networkingProtocols();
    }

    @Override
    public void onGridItemClickListener(Bundle clickedItemBundle) {
        Intent mine = new Intent(this,Recipe.class);
        mine.putExtra("Recipe Bundle", clickedItemBundle.getString(RECIPE_OBJECT));
        startActivity(mine);
    }
    
    public void networkingProtocols(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                recipeGrid.setAdapter(new RecipiesGridAdapter(MainActivity.this,response,
                        MainActivity.this));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }
}
