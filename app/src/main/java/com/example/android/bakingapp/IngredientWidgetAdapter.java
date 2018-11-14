package com.example.android.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IngredientWidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    JSONArray data;
    Context context;

    public IngredientWidgetAdapter(Context context){
        this.context = context;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String dataKey = preferences.getString("pref_recipe", "");
        String dataString = preferences.getString(dataKey, "");
        try {
            data = new JSONArray(dataString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_list_item);
        JSONObject o = data.optJSONObject(position);
        rv.setTextViewText(R.id.widget_list_item_text, o.optString("ingredient"));
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
