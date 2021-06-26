package com.application.posapplication.model;

public class ListUserStoriesModel {
//        private int MeetingID;
        private String Sentence;

    public ListUserStoriesModel(String sentence) {
//        MeetingID = meetingID;
        Sentence = sentence;
    }

//    public int getMeetingID() {
//        return MeetingID;
//    }

    public String getSentence() {
        return Sentence;
    }

//    public void setMeetingID(int meetingID) {
//        MeetingID = meetingID;
//    }

    public void setSentence(String sentence) {
        Sentence = sentence;
    }
}
