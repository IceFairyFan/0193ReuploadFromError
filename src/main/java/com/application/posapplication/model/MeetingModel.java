package com.application.posapplication.model;

import java.util.Date;

//nama Tabel harusnya MeetingTable
public class MeetingModel {
    private int id;
    private String title;
    private String date;
    private String input;

    public MeetingModel(int id, String title, String date, String input){
        this.id = id;
        this.title = title;
        this.date = date;
        this.input = input;
    }

    public String getTitle(){ return title; }

    public String getDate(){
        return date;
    }

    public String getInput(){
        return input;
    }

    public int getId() { return id; }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setInput(String input){
        this.input = input;
    }

    public void setId(int id) { this.id = id; }
}
