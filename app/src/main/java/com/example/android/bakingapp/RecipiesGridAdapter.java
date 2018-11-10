package com.example.android.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipiesGridAdapter extends RecyclerView.Adapter<RecipiesGridAdapter.MyViewHolder>{

    private Context mContext;
    private JSONArray mJSONArray;
    final private GridItemClickListener mListener;
    static ArrayList<RecipeModel> recipeList = new ArrayList<>();
    ArrayList<IngredientsFragment> mineIng = new ArrayList<>();
    ArrayList<Steps> mineSteps = new ArrayList<>();
    public static String RECIPE_OBJECT = "Recipe Object";

    public RecipiesGridAdapter(Context mContext, JSONArray mJSONArray,GridItemClickListener listener){
        this.mContext = mContext;
        this.mJSONArray = mJSONArray;
        mListener = listener;
    }

    interface GridItemClickListener{
        void onGridItemClickListener(Bundle recipeBundle);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.custom_recipie_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        int length = 0;
        if (mJSONArray == null)
            return length;
        /*try {

            length = mJSONArray.length();

        } catch (JSONException e) {
            Log.e("TAG", e.getLocalizedMessage());
            e.printStackTrace();
        }*/
        return mJSONArray.length();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView recepie_img;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            //recepie_img = (ImageView)itemView.findViewById(R.id.recipie_img);
            title = (TextView) itemView.findViewById(R.id.recipe_title);

            itemView.setOnClickListener(this);
        }

        void bind(int itemPosition){
            //JSONObject recipe = null;
            RecipeModel model = new RecipeModel();
            try {

                JSONObject recipe = mJSONArray.getJSONObject(itemPosition);
                int id = recipe.getInt("id");
                String name = recipe.getString("name");
                model.setId(id);
                model.setName(name);

                JSONArray ingredients = recipe.getJSONArray("ingredients");
                for(int i=0;i<=ingredients.length();i++){
                    JSONObject object = ingredients.getJSONObject(i);
                    int quantity = object.getInt("quantity");
                    String measure = object.getString("measure");
                    String ingredient = object.getString("ingredient");

                    /*Log.e("TAGGG",ingredient);
                    model.setQuantity(quantity);
                    model.setMeasure(measure);
                    model.setIngredient(ingredient);*/

                    //mineIng.add(new IngredientsFragment(quantity,measure,ingredient));
                    model.setMyIng(mineIng);
                }

                JSONArray steps = recipe.getJSONArray("steps");
                for(int i=0;i<=steps.length();i++){
                    JSONObject object = steps.getJSONObject(i);
                    int step_id = object.getInt("id");
                    String shortDescription = object.getString("shortDescription");
                    String description = object.getString("description");
                    String video = object.getString("videoURL");
                    String thumbnail = object.getString("thumbnail");

                    /*model.setStep_id(step_id);
                    model.setShortDesc(shortDescription);
                    model.setDec(description);
                    model.setVideo(video);
                    model.setThumbnail(thumbnail);*/
                    mineSteps.add(new Steps(step_id,shortDescription,description,video));
                    model.setMySteps(mineSteps);

                }
                int servings = recipe.getInt("servings");
                String image = recipe.getString("image");

                model.setServings(servings);
                model.setImage(image);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            recipeList.add(model);
            title.setText(model.getName());



        }

        @Override
        public void onClick(View v) {
            int clickedItemPosition = getAdapterPosition();
            Bundle b = new Bundle();
            b.putString(RECIPE_OBJECT, mJSONArray.optJSONObject(clickedItemPosition).toString());
            mListener.onGridItemClickListener(b);
        }
    }
}
