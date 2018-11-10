package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IngredientsFragment extends Fragment {

    JSONArray ingredients;
    RecyclerView mRecyclerView;

    public static IngredientsFragment NewInstance(String ing){
        Bundle b = new Bundle();
        b.putString("ING", ing);
        IngredientsFragment fragment = new IngredientsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ingredents, container, false);
        mRecyclerView = v.findViewById(R.id.ing_rec_v);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            ingredients = new JSONArray(getArguments().getString("ING"));
            //Log.e("LOg", "arr: "+ingredients.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRecyclerView.setAdapter(new IngredientsAdapter());
    }

    class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {


        @NonNull
        @Override
        public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredent_custom_view, parent, false);
            return new IngredientsViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
            JSONObject o = ingredients.optJSONObject(position);
            holder.measure.setText(o.optString("measure"));
            holder.ingredients.setText(o.optString("ingredient"));
            holder.quantity.setText(o.optString("quantity"));
        }


        @Override
        public int getItemCount() {
            return ingredients.length();
        }

        class IngredientsViewHolder extends RecyclerView.ViewHolder {
            TextView quantity, measure, ingredients;

            public IngredientsViewHolder(View itemView) {
                super(itemView);
                quantity = itemView.findViewById(R.id.quantity);
                ingredients = itemView.findViewById(R.id.ingredient);
                measure = itemView.findViewById(R.id.measure);
            }
        }
    }

}
