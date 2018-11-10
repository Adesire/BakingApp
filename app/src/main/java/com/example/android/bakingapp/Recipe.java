package com.example.android.bakingapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class Recipe extends AppCompatActivity implements RecipeDetailsFragment.RecipeClickListener {
    public static final String TAG = Recipe.class.getSimpleName();
    boolean isTablet = false;
    String ingredents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        String extra = getIntent().getStringExtra("Recipe Bundle");
        //Log.e(TAG, extra);
        if (savedInstanceState == null) {
            replaceMainContent(RecipeDetailsFragment.NewInstance(extra), false);
            try {
                JSONObject o = new JSONObject(extra);
                ingredents = o.optJSONArray("ingredients").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (findViewById(R.id.second_content_frame) != null) {
            isTablet = true;
            replaceSecondPaneContent(IngredientsFragment.NewInstance(ingredents), false);
        }

    }

    @Override
    public void onRecipeStepClicked(String steps, int pos) {
        if (!isTablet)
            replaceMainContent(StepsDetailFragment.NewInstance(steps, pos), true);
        else replaceSecondPaneContent(StepsDetailFragment.NewInstance(steps, pos), false);
    }

    @Override
    public void onIngredientButtonClicked(String ing) {
        if (!isTablet)
            replaceMainContent(IngredientsFragment.NewInstance(ing), true);
        else
            replaceSecondPaneContent(IngredientsFragment.NewInstance(ing), false);
    }

    private void replaceMainContent(Fragment fragment, boolean shouldAddBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content_frame, fragment);
        if (shouldAddBackStack)
            ft.addToBackStack(null);
        ft.commit();
    }

    private void replaceSecondPaneContent(Fragment fragment, boolean shouldAddBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.second_content_frame, fragment);
        if (shouldAddBackStack)
            ft.addToBackStack(null);
        ft.commit();
    }
}
