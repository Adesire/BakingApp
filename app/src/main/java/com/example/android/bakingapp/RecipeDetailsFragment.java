package com.example.android.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeDetailsFragment extends Fragment {

    View v;
    String data;
    RecyclerView stepsView;
    JSONArray steps, ingredents;
    Button ingredentsButton;
    RecipeClickListener mRecipeClickListener;


    public static RecipeDetailsFragment NewInstance(String data) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle b = new Bundle();
        b.putString("RCP", data);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        stepsView = v.findViewById(R.id.recipe_steps);
        ingredentsButton = v.findViewById(R.id.button_ingredents);
        stepsView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle dataBundle = getArguments();
        data = dataBundle.getString("RCP");
        try {
            JSONObject o = new JSONObject(data);
            steps = o.optJSONArray("steps");
            ingredents = o.optJSONArray("ingredients");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        stepsView.setAdapter(new RecipeDetailRecyclerAdatper());
        ingredentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecipeClickListener.onIngredientButtonClicked(ingredents.toString());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRecipeClickListener = (RecipeClickListener) context;
    }

    class RecipeDetailRecyclerAdatper extends RecyclerView.Adapter<RecipeDetailRecyclerAdatper.RecipeViewHolder> {

        public RecipeDetailRecyclerAdatper() {

        }


        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_steps_button, parent, false);
            return new RecipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            JSONObject o = steps.optJSONObject(position);
            holder.mButton.setText(o.optString("shortDescription"));
        }

        @Override
        public int getItemCount() {
            return steps.length();
        }

        class RecipeViewHolder extends RecyclerView.ViewHolder {
            Button mButton;

            public RecipeViewHolder(View itemView) {
                super(itemView);
                mButton = itemView.findViewById(R.id.button_step);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRecipeClickListener.onRecipeStepClicked(steps.toString(), getAdapterPosition());
                    }
                });
            }
        }
    }

    interface RecipeClickListener {
        void onRecipeStepClicked(String steps, int position);
        void onIngredientButtonClicked(String ing);
    }
}
