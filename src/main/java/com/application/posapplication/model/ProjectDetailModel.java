package com.application.posapplication.model;

public class ProjectDetailModel {
    private String noun;
    private String id;

    public ProjectDetailModel(String noun, String id) {
        this.noun = noun;
        this.id = id;
    }

    public String getNoun() {
        return noun;
    }

    public String getId() {
        return id;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public void setId(String id) {
        this.id = id;
    }
}
