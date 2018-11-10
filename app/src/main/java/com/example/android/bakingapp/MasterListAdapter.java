package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.TheViewHolder>{

    private Context mContext;
   // private List<> mstepsList;

    public MasterListAdapter(Context context, List<IngredientsFragment> ingredientsList) {
        mContext = context;
        //mIngredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public TheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TheViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TheViewHolder extends RecyclerView.ViewHolder {

        public TheViewHolder(View itemView) {
            super(itemView);
        }
    }
}
