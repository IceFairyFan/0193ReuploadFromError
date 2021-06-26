package com.application.posapplication.model;

public enum Type {

    NN("NN"),
    NNS("NNS"),
    NNP("NNP"),
    NNPS("NNPS"),
    VB("VB"),
    VBD("VBD"),
    VBG("VBG"),
    VBN("VBN"),
    VBP("VBP"),
    VBZ("VBZ");

    private String type;

    Type(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }
}
