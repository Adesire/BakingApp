package com.example.android.bakingapp;

import java.util.ArrayList;

public class RecipeModel {

    public String getName() {
        return name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getDec() {
        return dec;
    }

    public String getVideo() {
        return video;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStep_id() {
        return step_id;
    }

    public int getServings() {
        return servings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStep_id(int step_id) {
        this.step_id = step_id;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }



    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    private String name;
    private String ingredient;
    private String shortDesc;
    private String dec;
    private String video;
    private String measure;
    private String thumbnail;
    private String image;
    private int id,quantity,step_id,servings;

    public ArrayList<IngredientsFragment> getMyIng() {
        return myIng;
    }

    public void setMyIng(ArrayList<IngredientsFragment> myIng) {
        this.myIng = myIng;
    }

    private ArrayList<IngredientsFragment> myIng;

    public ArrayList<Steps> getMySteps() {
        return mySteps;
    }

    public void setMySteps(ArrayList<Steps> mySteps) {
        this.mySteps = mySteps;
    }

    private ArrayList<Steps> mySteps;

    public RecipeModel(int id,String name,String ingredient,
                       String shortDesc,String dec,String video,String thumbnail,
                       String image,int quantity,String measure,int step_id,int servings)
    {
        this.id = id;
        this.name = name;
        this.ingredient = ingredient;
        this.shortDesc = shortDesc;
        this.dec = dec;
        this.video = video;
        this.thumbnail = thumbnail;
        this.image = image;
        this.quantity = quantity;
        this.measure = measure;
        this.step_id = step_id;
        this.servings = servings;

    }
    public RecipeModel(int id, String name, String image, int servings,
                       ArrayList<IngredientsFragment> myIng, ArrayList<Steps> mySteps)
    {
        this.id = id;
        this.name = name;
        this.image = image;
        this.servings = servings;
        this.myIng = myIng;
        this.mySteps = mySteps;


    }
    public RecipeModel(){

    }
}
