package com.example.android.bakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class IngredientWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientWidgetAdapter(this);
    }
}
