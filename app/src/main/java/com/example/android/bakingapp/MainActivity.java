package com.example.android.bakingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.android.bakingapp.RecipiesGridAdapter.RECIPE_OBJECT;

public class MainActivity extends AppCompatActivity implements RecipiesGridAdapter.GridItemClickListener {

    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json" ;
    GridLayoutManager mLayoutManager;
    RecyclerView recipeGrid;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String PREF_DATA = "pref_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeGrid = (RecyclerView)findViewById(R.id.recipe_grid);
        mLayoutManager = new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
        recipeGrid.setLayoutManager(mLayoutManager);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.contains(PREF_DATA))
            networkingProtocols();
        else {
            try {
                showData(new JSONArray(sharedPreferences.getString(PREF_DATA, "")));
                Log.e("ddd", sharedPreferences.getString("4", "hh"));
            } catch (JSONException e) {
                e.printStackTrace();
                networkingProtocols();
            }
        }
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
                editor = sharedPreferences.edit();
                editor.putString(PREF_DATA, response.toString());
                for (int i = 0; i < response.length(); i++){
                    JSONObject o = response.optJSONObject(i);
                    String ing = o.optJSONArray("ingredients").toString();
                    editor.putString(String.valueOf(i+1), ing);
                }
                editor.apply();
                showData(response);
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

    private void showData(JSONArray data){
        recipeGrid.setAdapter(new RecipiesGridAdapter(MainActivity.this,data,
                MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Widget");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1){
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
