package com.example.android.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.widget_preference);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.e("AG", key);
        if (key.equals("pref_recipe")){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getActivity(), IngredientWidget.class));

            IngredientWidget.updateAllAppWidgets(getActivity(), appWidgetManager,appWidgetIds);
        }
    }
}
