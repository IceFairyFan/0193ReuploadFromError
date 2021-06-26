package com.application.posapplication.model;

public enum TypePosIdNoun {

//    NN("NN"),
//    NNS("NNS"),
//    NNP("NNP"),
//    NNPS("NNPS"),
//    VB("VB"),
//    VBD("VBD"),
//    VBG("VBG"),
//    VBN("VBN"),
//    VBP("VBP"),
//    VBZ("VBZ");

    NN("NN"),
    NNAC("NNAC"),
    NNPFC("NNPFC");

    private String typePosIdNoun;

    TypePosIdNoun(String typePosIdNoun){
        this.typePosIdNoun = typePosIdNoun;
    }

    public String getName(){ return typePosIdNoun; }
}
