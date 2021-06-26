package com.application.posapplication.model;

public class HomepagePageChangeModel {
    private String homepageProjectName;
    private String homepageProjectID;

    public HomepagePageChangeModel(String homepageProjectName, String homepageProjectID) {
        this.homepageProjectName = homepageProjectName;
        this.homepageProjectID = homepageProjectID;
    }

    public String getHomepageProjectName() {
        return homepageProjectName;
    }

    public String getHomepageProjectID() {
        return homepageProjectID;
    }

    public void setHomepageProjectName(String homepageProjectName) {
        this.homepageProjectName = homepageProjectName;
    }

    public void setHomepageProjectID(String homepageProjectID) {
        this.homepageProjectID = homepageProjectID;
    }
}
