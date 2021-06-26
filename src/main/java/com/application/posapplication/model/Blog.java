package com.application.posapplication.model;

public class Blog {
    private int id;
    private String title;
    private String content;
    private String keyPhrase;

    public Blog() {  }

    public Blog(int id, String title, String content) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setKeyPhrase(keyPhrase);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getKeyPhrase() { return keyPhrase; }

    public void setKeyPhrase(String keyPhrase) { this.keyPhrase = keyPhrase; }

    @Override
    public String toString(){
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
        //Blog{i

//        return "As a " + title +
//                ", I want to " + content +
//                ".";
    }

}
