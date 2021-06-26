package com.application.posapplication.model;

public class HomepageAddProjectModel {
    private int id;
    private String projectName;
    private String companyName;
    private String projectDescription;
    private String projectStatus ;

    public HomepageAddProjectModel(int id, String projectName, String companyName, String projectDescription, String projectStatus){
        this.id = id;
        this.projectName = projectName;
        this.companyName = companyName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
    }

    public int getId() { return id; }

    public String getProjectName(){
        return projectName;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String getProjectDescription(){
        return projectDescription;
    }

    public String getProjectStatus() { return projectStatus; }

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setProjectStatus(String projectStatus) { this.projectStatus = projectStatus; }
}
