package com.example.android.bakingapp;

public class Steps {
    private int id;
    private String shortDescr;
    private String description;
    private String video;

    public int getId() {
        return id;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public String getDescription() {
        return description;
    }

    public String getVideo() {
        return video;
    }

    public Steps(int id, String shortDescr, String description, String video) {
        this.id = id;
        this.shortDescr = shortDescr;
        this.description = description;
        this.video = video;
    }
}
